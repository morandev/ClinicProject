package ar.com.digitalhouse.ctd.clinicproject.model.service.impl;

import ar.com.digitalhouse.ctd.clinicproject.dto.PatientDto;
import ar.com.digitalhouse.ctd.clinicproject.model.entity.Patient;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IPatientService;
import ar.com.digitalhouse.ctd.clinicproject.repository.IPatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    @Autowired
    IPatientRepository patientDao;
    @Autowired
    ObjectMapper mapper;

    @Override
    public Set<PatientDto> getAll() {
        List<Patient> allPatients = patientDao.findAll();

        return allPatients.stream()
                .map( patient -> mapper.convertValue( patient , PatientDto.class ) )
                .collect( Collectors.toSet() );
    }

    @Override
    public void add( PatientDto patientDto ) {
        Patient pacient = mapper.convertValue( patientDto , Patient.class );
        patientDao.save( pacient );
    }

    @Override
    public PatientDto update( Long id , PatientDto patient ) {
        Optional< Patient > patientFromDb = patientDao.findById( id );

        if( patientFromDb.isPresent() ) {
            Patient patientConverted = mapper.convertValue( patient , Patient.class );
            patientConverted.setId( id );
            patientConverted = patientDao.save( patientConverted );

            return mapper.convertValue( patientConverted, PatientDto.class );
        }

        throw new RuntimeException( "Null on find patient by id: " + id );
    }

    @Override
    public void delete( Long id ) {
        if ( find(id) != null )
            patientDao.deleteById( id );
    }

    @Override
    public PatientDto find( Long id ) {
        Optional< Patient > patient = patientDao.findById( id );
        PatientDto patientDto;

        if ( patient.isPresent() ) {
            patientDto = mapper.convertValue( patient , PatientDto.class );
            return patientDto;
        }

        throw new RuntimeException( "Null on find patient by id: " + id );
    }
}
