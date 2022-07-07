package ar.com.digitalhouse.ctd.clinicproject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {

    private final String SECRET_KEY = "secret";

    public String extractUsername( String token ) {
        return extractClaimUsername( token );
    }

    public Date extractExpiration(String token ) {
        return extractClaimDate( token );
    }

    public Date extractClaimDate(String token ) {
        Claims claims = extractAllClaims( token );
        return claims.getExpiration();
    }

    public String extractClaimUsername( String token ) {
        Claims claims = extractAllClaims( token );
        return claims.getSubject();
    }

    public Claims extractAllClaims( String token ) {
        return Jwts.parser()
                .setSigningKey( SECRET_KEY )
                .parseClaimsJws( token )
                .getBody();
    }

    public String generateToken( UserDetails userDetails ) {
        Map< String, Object > claims = new HashMap<>();
        return createToken( claims, userDetails.getUsername() );
    }

    public String createToken( Map< String, Object > claims, String subject ) {
        return Jwts.builder()
                .setClaims( claims )
                .setSubject( subject )
                .setIssuedAt( new Date( System.currentTimeMillis() ) )
                .setExpiration( new Date( System.currentTimeMillis() + (1000 * 60) + (60 * 10) ) ) //TODO: Refactor
                .signWith( SignatureAlgorithm.HS256, SECRET_KEY )
                .compact();
    }

    public boolean validateToken( String token, UserDetails userDetails ) {
        final String username = extractUsername( token );
        return ( username.equals( userDetails.getUsername() ) && !isTokenExpired( token ) );
    }

    public boolean isTokenExpired( String token ) {
        return extractExpiration( token ).before( new Date() );
    }
}
