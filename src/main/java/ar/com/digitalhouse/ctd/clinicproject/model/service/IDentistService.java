package ar.com.digitalhouse.ctd.clinicproject.model.service;

import ar.com.digitalhouse.ctd.clinicproject.dto.DentistDto;

import java.util.Optional;
import java.util.Set;

public interface IDentistService {

    Set<DentistDto> getAll();
    Optional<DentistDto> add( DentistDto dentist );
    Optional<DentistDto> update( Long id , DentistDto dentist );
    Optional<DentistDto> find( Long id );
    void delete( Long id );
    Optional<DentistDto> findByEnrollment( String enrollment );
    boolean validate( DentistDto dentistDto );

}
