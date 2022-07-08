package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.UserDto;
import ar.com.digitalhouse.ctd.clinicproject.dto.UserAuthenticationDto;
import ar.com.digitalhouse.ctd.clinicproject.model.login.Rol;
import ar.com.digitalhouse.ctd.clinicproject.model.login.User;
import ar.com.digitalhouse.ctd.clinicproject.repository.IUserRepository;
import ar.com.digitalhouse.ctd.clinicproject.utils.JWTUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping( value = "/authenticate" )
public class AuthenticationController {
    private final Logger logger = Logger.getLogger( AuthenticationController.class );
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final IUserRepository userDao;
    private JWTUtils jwtUtils;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JWTUtils jwtUtils, IUserRepository userDao ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @Valid @RequestBody UserAuthenticationDto authenticationRequest ) {

        Map< String, String > body = new HashMap<>();

        try {

            Authentication auth = authenticationManager
                .authenticate( new UsernamePasswordAuthenticationToken( authenticationRequest.getUsername(),
                                                                        authenticationRequest.getPassword() ) );

            if ( auth.isAuthenticated() ) {
                logger.info("User Logged In");

                final UserDetails userDetails = userDetailsService
                        .loadUserByUsername( authenticationRequest.getUsername() );

                String token = jwtUtils.generateToken( userDetails );
                body.put( "jwt", token );
                return ResponseEntity.status( 201 ).body( body );
            } else {
                logger.info("User enters invalid credentials");
                return ResponseEntity.badRequest().body("Invalid Credentials");
            }
        } catch ( BadCredentialsException e ) {
            return ResponseEntity.badRequest().body("Invalid Credentials");
        } catch ( Exception e ) {
            return ResponseEntity.status(500).body("Something went wrong");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register( @Valid @RequestBody UserDto userDto ) {

        if ( userDao.findByUsername( userDto.getUsername() ).isPresent() )
            return ResponseEntity.badRequest().body("User already exists");

        if ( userDao.findByEmail( userDto.getEmail() ).isPresent() )
            return ResponseEntity.badRequest().body("Email already exists");

        Map< String, String > body = new HashMap<>();

        User user = new User();
        user.setName( userDto.getName() );
        user.setLastName( userDto.getLastName() );
        user.setUsername( userDto.getUsername() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( new BCryptPasswordEncoder().encode( userDto.getPassword() ) );
        user.setRol( Rol.ROLE_ADMIN );

        userDao.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername( userDto.getUsername() );

        String token = jwtUtils.generateToken( userDetails );
        body.put( "jwt", token );

        logger.info("User successfully registered");
        return ResponseEntity.status( 201 ).body( body );

    }
}
