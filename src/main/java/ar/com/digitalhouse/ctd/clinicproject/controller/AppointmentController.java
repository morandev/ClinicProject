package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.AppointmentDto;
import ar.com.digitalhouse.ctd.clinicproject.exception.ResourceNotFoundException;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAppointmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping( "/appointments" )
public class AppointmentController {

    private final Logger logger = Logger.getLogger( AppointmentController.class );
    private final IAppointmentService appointmentService;
    @Autowired
    public AppointmentController( IAppointmentService appointmentService ) { this.appointmentService = appointmentService; }

    @PostMapping( path = "/add" )
    public ResponseEntity<AppointmentDto> add( @Valid @RequestBody AppointmentDto appointmentDto ) {

        if ( appointmentService.validate( appointmentDto ) ) {
            if ( appointmentService.validateParticipants( appointmentDto ) ) {

                Optional<AppointmentDto> appointmentDtoDb = appointmentService.add( appointmentDto );

                if( appointmentDtoDb.isPresent() ) {
                    URI uri = ServletUriComponentsBuilder
                            .fromCurrentServletMapping()
                            .path("/appointments/{id}")
                            .buildAndExpand( appointmentDtoDb.get().getId() )
                            .toUri();

                    return ResponseEntity.created( uri ).body( appointmentDtoDb.get() );
                }

                return ResponseEntity.notFound().build();
            }
        }

        return new ResponseEntity<>( HttpStatus.CONFLICT );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) throws ResourceNotFoundException {

        if( appointmentService.find( id ).isPresent() ) {
            appointmentService.delete( id );
            return ResponseEntity.noContent().build();
        }

        throw new ResourceNotFoundException("id: " + id + " not found");
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<AppointmentDto> find( @PathVariable Long id ) throws ResourceNotFoundException {
        Optional<AppointmentDto> appointmentDto = appointmentService.find( id );

        if( appointmentDto.isPresent() ) {
            return ResponseEntity.ok( appointmentDto.get() );
        }

        throw new ResourceNotFoundException("id: " + id + " not found");
    }

    @GetMapping
    public ResponseEntity< Collection<AppointmentDto> > getAll() { return ResponseEntity.ok( appointmentService.getAll() ); }

    @PutMapping( "/{id}" )
    public ResponseEntity<AppointmentDto> update( @PathVariable Long id , @Valid @RequestBody AppointmentDto appointment ) throws ResourceNotFoundException {
        Optional<AppointmentDto> appointmentDto = appointmentService.update( id , appointment );

        if( appointmentDto.isPresent() ) {
            return ResponseEntity.ok( appointmentDto.get() );
        }

        throw new ResourceNotFoundException("id: " + id + " not found");
    }


}
