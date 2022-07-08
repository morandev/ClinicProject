package ar.com.digitalhouse.ctd.clinicproject.model.service.impl;

import ar.com.digitalhouse.ctd.clinicproject.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository userDao;
    @Autowired
    public UserDetailsServiceImpl( IUserRepository userDao ) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Optional< ar.com.digitalhouse.ctd.clinicproject.model.login.User > user = userDao.findByUsername( username );

        if ( user.isPresent() ) {

            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add( new SimpleGrantedAuthority( user.get().getRol().name() ) );
            return new User( user.get().getUsername(), user.get().getPassword(), authorityList );
        }

        throw new UsernameNotFoundException("Username: " + username + ": not found");
    }
}
