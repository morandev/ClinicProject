package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.PatientDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping( "/patients" )
public class PatientController {
    private final IPatientService patientService;
    @Autowired
    public PatientController( IPatientService patientService ) {
        this.patientService = patientService;
    }

    @PostMapping( path = "/add" )
    public ResponseEntity<PatientDto> add( @RequestBody PatientDto patient ) {
        Optional<PatientDto> patientDto = patientService.add( patient );

        if( patientDto.isPresent() ) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand( patientDto.get().getId() )
                    .toUri();

            return ResponseEntity.created( uri ).body( patientDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {
        patientService.delete( id );
        return ResponseEntity.noContent().build();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<PatientDto> find( @PathVariable Long id ) {
        Optional<PatientDto> patientDto = patientService.find( id );

        if( patientDto.isPresent() ) {
            return ResponseEntity.ok( patientDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity< Collection<PatientDto> > getAll() {
        return ResponseEntity.ok( patientService.getAll() );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<PatientDto> update( @PathVariable Long id , @RequestBody PatientDto patient ) {
        Optional<PatientDto> patientDto = patientService.update( id , patient );

        if( patientDto.isPresent() ) {
            return ResponseEntity.ok( patientDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

}
