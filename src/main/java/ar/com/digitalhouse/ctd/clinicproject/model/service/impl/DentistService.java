package ar.com.digitalhouse.ctd.clinicproject.model.service.impl;

import ar.com.digitalhouse.ctd.clinicproject.dto.DentistDto;
import ar.com.digitalhouse.ctd.clinicproject.model.entity.Dentist;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IDentistService;
import ar.com.digitalhouse.ctd.clinicproject.repository.IDentistRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DentistService implements IDentistService {

    private IDentistRepository dentistDao;
    private ObjectMapper mapper;
    @Autowired
    public DentistService( IDentistRepository dentistDao, ObjectMapper mapper ) {
        this.dentistDao = dentistDao;
        this.mapper = mapper;
    }
    @Override
    public Set<DentistDto> getAll() {
        List<Dentist> allDentists = dentistDao.findAll();

        return allDentists.stream()
                .map( dentist -> mapper.convertValue( dentist, DentistDto.class ) )
                .collect( Collectors.toSet() );
    }

    @Override
    public Optional<DentistDto> add( DentistDto dentistDto ) {
        Dentist dentist = mapper.convertValue( dentistDto, Dentist.class );
        Optional<DentistDto> dto = Optional.of( mapper.convertValue( dentistDao.save( dentist ), DentistDto.class ) );
        return dto;
    }

    @Override
    public Optional<DentistDto> update( Long id, DentistDto dentist ) {

        Optional<Dentist> dentistFromDb = dentistDao.findById( id );
        Optional<DentistDto> dentistDto = Optional.empty();

        if( dentistFromDb.isPresent() ) {

            Dentist dentistConverted = mapper.convertValue( dentist , Dentist.class );
            dentistConverted.setId( id );
            dentistConverted = dentistDao.save( dentistConverted );
            dentistDto = Optional.of( mapper.convertValue( dentistConverted , DentistDto.class ) );

            return dentistDto;
        }

        return dentistDto;
    }

    @Override
    public void delete( Long id ) {
        if ( find(id) != null )
            dentistDao.deleteById( id );
    }

    @Override
    public Optional<DentistDto> find( Long id ) {
        Optional<Dentist> dentist = dentistDao.findById( id );
        Optional<DentistDto> dentistDto = Optional.empty();

        if( dentist.isPresent() )
            dentistDto = Optional.of( mapper.convertValue( dentist , DentistDto.class ) );

        return dentistDto;
    }

    @Override
    public Optional<DentistDto> findByEnrollment( String enrollment ) {
        Optional<Dentist> dentist = dentistDao.findByEnrollment( enrollment );
        Optional<DentistDto> dentistDto = Optional.empty();

        if( dentist.isPresent() )
            dentistDto = Optional.of( mapper.convertValue( dentist , DentistDto.class ) );

        return dentistDto;
    }
}
