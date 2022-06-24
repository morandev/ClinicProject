package ar.com.digitalhouse.ctd.clinicproject.repository;

import ar.com.digitalhouse.ctd.clinicproject.model.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDentistRepository extends JpaRepository< Dentist , Long > {
    @Query( value = "SELECT d FROM Dentist d WHERE d.enrollment = ?1" )
    Optional<Dentist> findByEnrollment( String enrollment );
}
