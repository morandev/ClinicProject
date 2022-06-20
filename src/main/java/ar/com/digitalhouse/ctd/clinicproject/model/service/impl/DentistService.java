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

    @Autowired
    IDentistRepository dentistDao;
    @Autowired
    ObjectMapper mapper;

    @Override
    public Set<DentistDto> getAll() {
        List<Dentist> allDentists = dentistDao.findAll();

        return allDentists.stream()
                                .map( dentist -> mapper.convertValue( dentist, DentistDto.class ) )
                                .collect( Collectors.toSet() );
    }

    @Override
    public void add( DentistDto dentistDto ) {
        Dentist dentist = mapper.convertValue( dentistDto, Dentist.class );
        dentistDao.save( dentist );
    }

    @Override
    public DentistDto update( Long id, DentistDto dentist ) {
        Optional< Dentist > dentistFromDb = dentistDao.findById( id );

        if( dentistFromDb.isPresent() ) {
            Dentist dentistConverted = mapper.convertValue( dentist , Dentist.class );
            dentistConverted.setId( id );
            dentistConverted = dentistDao.save( dentistConverted );

            return mapper.convertValue( dentistConverted, DentistDto.class );
        }

        throw new RuntimeException( "Null on find dentist by id: " + id );
    }

    @Override
    public void delete( Long id ) {
        if ( find(id) != null )
            dentistDao.deleteById( id );
    }

    @Override
    public DentistDto find( Long id ) {
        Optional< Dentist > dentist = dentistDao.findById( id );
        DentistDto dentistDto;

        if ( dentist.isPresent() ) {
            dentistDto = mapper.convertValue( dentist , DentistDto.class );
            return dentistDto;
        }

        throw new RuntimeException( "Null on find dentist by id: " + id );
    }
}
