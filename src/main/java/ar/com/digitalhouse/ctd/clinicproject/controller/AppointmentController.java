package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.AppointmentDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping( "/appointments" )
public class AppointmentController {
    @Autowired
    IAppointmentService appointmentService;

    @PostMapping( path = "/add" )
    public ResponseEntity add( @RequestBody AppointmentDto appointment ) {
        appointmentService.add( appointment );
        return new ResponseEntity( HttpStatus.BAD_REQUEST );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity delete( @PathVariable Long id ) {
        appointmentService.delete( id );
        return new ResponseEntity( HttpStatus.NO_CONTENT );
    }

    @GetMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<AppointmentDto> find( @PathVariable Long id ) {
        return ResponseEntity.ok( appointmentService.find( id ) );
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity< Collection<AppointmentDto> > getAll() {
        return ResponseEntity.ok( appointmentService.getAll() );
    }

    @PutMapping( "/{id}" )
    @ResponseBody
    public ResponseEntity<AppointmentDto> update( @PathVariable Long id , @RequestBody AppointmentDto appointment ) {
        return ResponseEntity.ok( appointmentService.update( id , appointment ) );
    }

}
