package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.AddressDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping( "/addresses" )
public class AddressController {

    //TODO: Buena practica en inyeccion de dependencias
    @Autowired
    IAddressService addressService;

    @PostMapping( path = "/add" )
    public ResponseEntity add( @RequestBody AddressDto address ) {
        addressService.add( address );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {
        addressService.delete( id );
        return new ResponseEntity( HttpStatus.NO_CONTENT );
        //TODO: Tratar NotFoundException
    }

    @GetMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<AddressDto> find( @PathVariable Long id ) {
        return ResponseEntity.ok( addressService.find( id ) );
        //TODO: Tratar NotFoundException
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<AddressDto>> getAll() {
        return ResponseEntity.ok( addressService.getAll() );
    }

    @PutMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<AddressDto> update( @PathVariable Long id , @RequestBody AddressDto address ) {
        return ResponseEntity.ok( addressService.update( id , address ) );
        //TODO: Tratar NotFoundException
    }

}
