package ar.com.digitalhouse.ctd.clinicproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping( "/users" )
public class UserController {

    @GetMapping
    public ResponseEntity<?> getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put( "username", authentication.getName() );
        return ResponseEntity.status( 200 ).body( userMap );
    }
}
