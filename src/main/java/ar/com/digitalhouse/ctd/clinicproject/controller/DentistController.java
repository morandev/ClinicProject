package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.DentistDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IDentistService;
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
@RequestMapping( "/dentists" )
public class DentistController {
    private final IDentistService dentistService;
    @Autowired
    public DentistController( IDentistService dentistService ) {
        this.dentistService = dentistService;
    }

    @PostMapping( path = "/add" )
    public ResponseEntity<DentistDto> add( @Valid @RequestBody DentistDto dentist ) {

        if ( !dentistService.validate( dentist ) ) {

            Optional<DentistDto> opDentistDto = dentistService.add( dentist );

            if ( opDentistDto.isPresent() ) {
                URI uri = ServletUriComponentsBuilder
                        .fromCurrentServletMapping()
                        .path("/dentists/{id}")
                        .buildAndExpand( opDentistDto.get().getId() )
                        .toUri();

                return ResponseEntity.created( uri ).body( opDentistDto.get() );
            }

            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>( HttpStatus.CONFLICT );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {

        if( dentistService.find( id ).isPresent() ) {
            dentistService.delete( id );
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
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
    public ResponseEntity<DentistDto> update( @PathVariable Long id , @Valid @RequestBody DentistDto dentistDto ) {
        Optional<DentistDto> opDentistDto = dentistService.update( id , dentistDto );

        if( opDentistDto.isPresent() ) {
            return ResponseEntity.ok( opDentistDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

}
