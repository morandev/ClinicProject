package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.AddressDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping( "/addresses" )
public class AddressController {
    private final IAddressService addressService;
    @Autowired
    public AddressController( IAddressService addressService ) {
        this.addressService = addressService;
    }

    @PostMapping( path = "/add" )
    public ResponseEntity<AddressDto> add( @RequestBody AddressDto address ) {
        Optional<AddressDto> addressDto = addressService.add( address );

        if( addressDto.isPresent() ) {
            URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand( addressDto.get().getId() )
                        .toUri();

            return ResponseEntity.created( uri ).body( addressDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {
        addressService.delete( id );
        return ResponseEntity.noContent().build();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<AddressDto> find( @PathVariable Long id ) {
        Optional<AddressDto> addressDto = addressService.find( id );

        if( addressDto.isPresent() ) {
            return ResponseEntity.ok( addressDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Collection<AddressDto>> getAll() {
        return ResponseEntity.ok( addressService.getAll() );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<AddressDto> update( @PathVariable Long id , @RequestBody AddressDto address ) {
        Optional<AddressDto> addressDto = addressService.update( id , address );

        if( addressDto.isPresent() ) {
            return ResponseEntity.ok( addressDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

}
