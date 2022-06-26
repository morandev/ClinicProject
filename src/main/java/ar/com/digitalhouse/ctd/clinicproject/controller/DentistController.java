package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.DentistDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping( "/dentists" )
public class DentistController {
    private final IDentistService dentistService;
    @Autowired
    public DentistController( IDentistService dentistService ) {
        this.dentistService = dentistService;
    }

    @PostMapping( path = "/add" )
    public ResponseEntity<DentistDto> add( @RequestBody DentistDto dentist ) {
        Optional<DentistDto> opDentistDto = dentistService.add( dentist );

        if( opDentistDto.isPresent() ) {
            URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand( opDentistDto.get().getId() )
                        .toUri();
            
            return ResponseEntity.created( uri ).body( opDentistDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {
        dentistService.delete( id );
        return ResponseEntity.noContent().build();
    }

    @GetMapping( "/" )
    public ResponseEntity<DentistDto> findByEnrollment( @RequestParam( required = false ) String enrollment) {
        Optional<DentistDto> opDentistDto = dentistService.findByEnrollment( enrollment );

        if( opDentistDto.isPresent() ) {
            return ResponseEntity.ok( opDentistDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<DentistDto> find( @PathVariable Long id ) {
        Optional<DentistDto> opDentistDto = dentistService.find( id );

        if( opDentistDto.isPresent() ) {
            return ResponseEntity.ok( opDentistDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity< Collection<DentistDto> > getAll() {
        return ResponseEntity.ok( dentistService.getAll() );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<DentistDto> update( @PathVariable Long id , @RequestBody DentistDto dentistDto ) {
        Optional<DentistDto> opDentistDto = dentistService.update( id , dentistDto );

        if( opDentistDto.isPresent() ) {
            return ResponseEntity.ok( opDentistDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

}
