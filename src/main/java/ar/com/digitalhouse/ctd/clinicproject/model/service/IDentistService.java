package ar.com.digitalhouse.ctd.clinicproject.model.service;

import ar.com.digitalhouse.ctd.clinicproject.dto.DentistDto;

import java.util.Set;

public interface IDentistService {

    Set<DentistDto> getAll();
    void add( DentistDto dentist );
    DentistDto update( Long id , DentistDto dentist );
    void delete( Long id );
    DentistDto find( Long id );

}
