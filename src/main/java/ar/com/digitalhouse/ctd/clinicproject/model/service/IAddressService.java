package ar.com.digitalhouse.ctd.clinicproject.model.service;

import ar.com.digitalhouse.ctd.clinicproject.dto.AddressDto;

import java.util.Set;

public interface IAddressService {

    Set<AddressDto> getAll();
    void add( AddressDto appointment );
    AddressDto update( Long id , AddressDto appointment );
    void delete( Long id );
    AddressDto find( Long id );

}
