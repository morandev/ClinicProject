package ar.com.digitalhouse.ctd.clinicproject.model.service.impl;

import ar.com.digitalhouse.ctd.clinicproject.dto.AddressDto;
import ar.com.digitalhouse.ctd.clinicproject.model.entity.Address;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAddressService;
import ar.com.digitalhouse.ctd.clinicproject.repository.IAddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressService implements IAddressService {

    @Autowired
    IAddressRepository addressDao;
    @Autowired
    ObjectMapper mapper;

    @Override
    public Set<AddressDto> getAll() {
        List<Address> allAppointments = addressDao.findAll();

        return allAppointments.stream()
                .map( address -> mapper.convertValue( address , AddressDto.class ) )
                .collect( Collectors.toSet() );
    }

    @Override
    public void add( AddressDto addressDto ) {
        Address address = mapper.convertValue( addressDto, Address.class );
        addressDao.save( address );
    }

    @Override
    public AddressDto update( Long id , AddressDto address ) {
        Optional< Address > addressFromDb = addressDao.findById( id );

        if( addressFromDb.isPresent() ) {
            Address addressConverter = mapper.convertValue( address , Address.class );
            addressConverter.setId( id );
            addressConverter = addressDao.save( addressConverter );

            return mapper.convertValue( addressConverter , AddressDto.class );
        }

        throw new RuntimeException( "Null on find address by id: " + id );
    }

    @Override
    public void delete( Long id ) {
        if ( find(id) != null )
            addressDao.deleteById( id );
    }

    @Override
    public AddressDto find( Long id ) {
        Optional< Address > address = addressDao.findById( id );
        AddressDto addressDto;

        if ( address.isPresent() ) {
            addressDto = mapper.convertValue( address , AddressDto.class );
            return addressDto;
        }

        throw new RuntimeException( "Null on find address by id: " + id );
    }

}
