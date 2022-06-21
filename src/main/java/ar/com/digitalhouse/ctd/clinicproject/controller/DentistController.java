package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.DentistDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping( "/dentists" )
public class DentistController {

    //TODO: Buena practica en inyeccion de dependencias
    @Autowired
    IDentistService dentistService;

    @PostMapping( path = "/add" )
    public ResponseEntity add( @RequestBody DentistDto dentist ) {
        dentistService.add( dentist );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {
        dentistService.delete( id );
        return new ResponseEntity( HttpStatus.NO_CONTENT );
        //TODO: Tratar NotFoundException
    }

    @GetMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<DentistDto> find( @PathVariable Long id ) {
        return ResponseEntity.ok( dentistService.find( id ) );
        //TODO: Tratar NotFoundException
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity< Collection<DentistDto> > getAll() {
        return ResponseEntity.ok( dentistService.getAll() );
    }

    @PutMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<DentistDto> update( @PathVariable Long id , @RequestBody DentistDto dentist ) {
        return ResponseEntity.ok( dentistService.update( id , dentist ) );
        //TODO: Tratar NotFoundException
    }

}
