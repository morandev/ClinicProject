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

    private final IAddressRepository addressDao;
    private final ObjectMapper mapper;
    @Autowired
    public AddressService( IAddressRepository addressDao, ObjectMapper mapper ) {
        this.addressDao = addressDao;
        this.mapper = mapper;
    }
    @Override
    public Set<AddressDto> getAll() {
        List<Address> allAppointments = addressDao.findAll();

        return allAppointments.stream()
                .map( address -> mapper.convertValue( address , AddressDto.class ) )
                .collect( Collectors.toSet() );
    }

    @Override
    public Optional<AddressDto> add( AddressDto addressDto ) {
        Address address = mapper.convertValue( addressDto, Address.class );
        Optional<AddressDto> dto = Optional.of( mapper.convertValue( addressDao.save( address ), AddressDto.class ) );
        return dto;
    }

    @Override
    public Optional<AddressDto> update( Long id , AddressDto address ) {

        Optional<Address> addressFromDb = addressDao.findById( id );
        Optional<AddressDto> addressDto = Optional.empty();

        if( addressFromDb.isPresent() ) {

            Address addressConverted = mapper.convertValue( address , Address.class );
            addressConverted.setId( id );
            addressConverted = addressDao.save( addressConverted );
            addressDto = Optional.of( mapper.convertValue( addressConverted , AddressDto.class ) );

            return addressDto;
        }

        return addressDto;
    }

    @Override
    public void delete( Long id ) {
        if ( find(id) != null )
            addressDao.deleteById( id );
    }

    @Override
    public boolean validate( AddressDto addressDto ) {
        return addressDao.existsById( addressDto.getId() );
    }

    @Override
    public Optional<AddressDto> find( Long id ) {
        Optional<Address> address = addressDao.findById( id );
        Optional<AddressDto> addressDto = Optional.empty();

        if ( address.isPresent() )
            addressDto = Optional.of( mapper.convertValue( address, AddressDto.class ) );

        return addressDto;
    }
}
