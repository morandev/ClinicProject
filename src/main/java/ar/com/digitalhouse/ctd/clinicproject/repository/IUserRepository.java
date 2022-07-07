package ar.com.digitalhouse.ctd.clinicproject.repository;

import ar.com.digitalhouse.ctd.clinicproject.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository< User, Long > {
    @Query( value = "SELECT u FROM User u WHERE u.username = ?1" )
    Optional<User> findByUsername( String username );

    @Query( value = "SELECT u FROM User u WHERE u.email = ?1" )
    Optional<User> findByEmail( String email );
}
