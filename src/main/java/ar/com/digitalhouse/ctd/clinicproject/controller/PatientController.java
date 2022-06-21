package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.PatientDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping( "/patients" )
public class PatientController {

    //TODO: Buena practica en inyeccion de dependencias
    @Autowired
    IPatientService patientService;

    @PostMapping( path = "/add" )
    public ResponseEntity add( @RequestBody PatientDto patient ) {
        patientService.add( patient );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {
        patientService.delete( id );
        return new ResponseEntity( HttpStatus.NO_CONTENT );
        //TODO: Tratar NotFoundException
    }

    @GetMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<PatientDto> find( @PathVariable Long id ) {
        return ResponseEntity.ok( patientService.find( id ) );
        //TODO: Tratar NotFoundException
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity< Collection<PatientDto> > getAll() {
        return ResponseEntity.ok( patientService.getAll() );
    }

    @PutMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<PatientDto> update( @PathVariable Long id , @RequestBody PatientDto patient ) {
        return ResponseEntity.ok( patientService.update( id , patient ) );
        //TODO: Tratar NotFoundException
    }

}
