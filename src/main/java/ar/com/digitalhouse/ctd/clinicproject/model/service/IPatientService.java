package ar.com.digitalhouse.ctd.clinicproject.model.service;

import ar.com.digitalhouse.ctd.clinicproject.dto.PatientDto;

import java.util.Set;

public interface IPatientService {

    Set<PatientDto> getAll();
    void add( PatientDto patient );
    PatientDto update( Long id , PatientDto patient );
    void delete( Long id );
    PatientDto find( Long id);

}
