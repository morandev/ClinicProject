package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.AddressDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<AddressDto> add( @Valid @RequestBody AddressDto address ) {

        if ( !addressService.validate( address ) ) {

            Optional<AddressDto> addressDto = addressService.add( address );

            if ( addressDto.isPresent() ) {
                URI uri = ServletUriComponentsBuilder
                        .fromCurrentServletMapping()
                        .path("/addresses/{id}")
                        .buildAndExpand( addressDto.get().getId() )
                        .toUri();

                return ResponseEntity.created( uri ).body( addressDto.get() );
            }

            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>( HttpStatus.CONFLICT );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {

        if( addressService.find( id ).isPresent() ) {
            addressService.delete( id );
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
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
    public ResponseEntity<AddressDto> update( @PathVariable Long id , @Valid @RequestBody AddressDto address ) {
        Optional<AddressDto> addressDto = addressService.update( id , address );

        if( addressDto.isPresent() ) {
            return ResponseEntity.ok( addressDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

}
