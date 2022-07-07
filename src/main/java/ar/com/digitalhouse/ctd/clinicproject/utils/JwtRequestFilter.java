package ar.com.digitalhouse.ctd.clinicproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JWTUtils jwtUtils;

    @Autowired
    public JwtRequestFilter( UserDetailsService userDetailsService, JWTUtils jwtUtils ) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String username = "";
        String jwt = "";

        if ( authHeader != null && authHeader.startsWith("Bearer") ) {
            jwt = authHeader.substring(7);
            username = jwtUtils.extractUsername( jwt );
        }

        if ( !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null ) {

            UserDetails userDetails = userDetailsService.loadUserByUsername( username );

            if( jwtUtils.validateToken( jwt, userDetails ) ) {
                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities() );
                userPassAuthToken.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );
                SecurityContextHolder.getContext().setAuthentication( userPassAuthToken );
            }
        }

        filterChain.doFilter( request , response );
    }

}
