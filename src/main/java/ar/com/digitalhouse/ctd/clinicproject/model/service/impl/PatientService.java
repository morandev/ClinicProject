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
    private final IPatientRepository patientDao;
    private final ObjectMapper mapper;
    @Autowired
    public PatientService( IPatientRepository patientDao, ObjectMapper mapper ) {
        this.patientDao = patientDao;
        this.mapper = mapper;
    }

    public Set<PatientDto> getAll() {
        List<Patient> allPatients = patientDao.findAll();

        return allPatients.stream()
                .map( patient -> mapper.convertValue( patient , PatientDto.class ) )
                .collect( Collectors.toSet() );
    }

    @Override
    public Optional<PatientDto> add( PatientDto patientDto ) {
        Patient patient = mapper.convertValue( patientDto , Patient.class );
        Optional<PatientDto> dto = Optional.of( mapper.convertValue( patientDao.save( patient ), PatientDto.class ) );
        return dto;
    }

    @Override
    public Optional<PatientDto> update( Long id , PatientDto patient ) {

        Optional<Patient> patientFromDb = patientDao.findById( id );
        Optional<PatientDto> patientDto = Optional.empty();

        if( patientFromDb.isPresent() ) {

            Patient patientConverted = mapper.convertValue( patient , Patient.class );
            patientConverted.setId( id );
            patientConverted = patientDao.save( patientConverted );
            patientDto = Optional.of( mapper.convertValue( patientConverted , PatientDto.class ) );

            return patientDto;
        }

        return patientDto;
    }

    @Override
    public void delete( Long id ) {
        patientDao.deleteById( id );
    }

    @Override
    public boolean validate( PatientDto patientDto ) {
        return patientDao.existsById( patientDto.getId() );
    }

    @Override
    public Optional<PatientDto> find( Long id ) {
        Optional<Patient> patient = patientDao.findById( id );
        Optional<PatientDto> patientDto = Optional.empty();

        if ( patient.isPresent() )
            patientDto = Optional.of( mapper.convertValue( patient , PatientDto.class ) );

        return patientDto;
    }
}
