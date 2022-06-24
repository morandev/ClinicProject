package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.AppointmentDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping( "/appointments" )
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @Autowired
    public AppointmentController( IAppointmentService appointmentService ) {
        this.appointmentService = appointmentService;
    }

    @PostMapping( path = "/add" )
    public ResponseEntity<AppointmentDto> add( @RequestBody AppointmentDto appointment ) {
        Optional<AppointmentDto> appointmentDto = appointmentService.add( appointment );

        if( appointmentDto.isPresent() ) {
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand( appointmentDto.get().getId() )
                    .toUri();

            return ResponseEntity.created( uri ).body( appointmentDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {
        appointmentService.delete( id );
        return ResponseEntity.noContent().build();
    }

    @GetMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<AppointmentDto> find( @PathVariable Long id ) {
        Optional<AppointmentDto> appointmentDto = appointmentService.find( id );

        if( appointmentDto.isPresent() ) {
            return ResponseEntity.ok( appointmentDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity< Collection<AppointmentDto> > getAll() { return ResponseEntity.ok( appointmentService.getAll() ); }

    @PutMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<AppointmentDto> update( @PathVariable Long id , @RequestBody AppointmentDto appointment ) {
        Optional<AppointmentDto> appointmentDto = appointmentService.update( id , appointment );

        if( appointmentDto.isPresent() ) {
            return ResponseEntity.ok( appointmentDto.get() );
        }

        return ResponseEntity.notFound().build();
    }

}
