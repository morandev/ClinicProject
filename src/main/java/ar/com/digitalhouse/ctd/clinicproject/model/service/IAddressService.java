package ar.com.digitalhouse.ctd.clinicproject.model.service;

import ar.com.digitalhouse.ctd.clinicproject.dto.AddressDto;
import ar.com.digitalhouse.ctd.clinicproject.dto.AppointmentDto;

import java.util.Optional;
import java.util.Set;

public interface IAddressService {

    Set<AddressDto> getAll();
    Optional<AddressDto> add( AddressDto appointment );
    Optional<AddressDto> update( Long id , AddressDto appointment );
    Optional<AddressDto> find( Long id );
    void delete( Long id );
    boolean validate( AddressDto addressDto );

}
