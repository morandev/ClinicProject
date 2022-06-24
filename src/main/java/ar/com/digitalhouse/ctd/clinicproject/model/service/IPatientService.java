package ar.com.digitalhouse.ctd.clinicproject.model.service;

import ar.com.digitalhouse.ctd.clinicproject.dto.PatientDto;

import java.util.Optional;
import java.util.Set;

public interface IPatientService {

    Set<PatientDto> getAll();
    Optional<PatientDto> add( PatientDto patient );
    Optional<PatientDto> update( Long id , PatientDto patient );
    Optional<PatientDto> find( Long id );
    void delete( Long id );

}
