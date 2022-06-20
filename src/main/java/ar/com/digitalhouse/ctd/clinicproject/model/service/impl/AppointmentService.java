package ar.com.digitalhouse.ctd.clinicproject.model.service.impl;

import ar.com.digitalhouse.ctd.clinicproject.dto.AppointmentDto;
import ar.com.digitalhouse.ctd.clinicproject.model.entity.Appointment;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAppointmentService;
import ar.com.digitalhouse.ctd.clinicproject.repository.IAppointmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    IAppointmentRepository appointmentDao;
    @Autowired
    ObjectMapper mapper;

    @Override
    public Set<AppointmentDto> getAll() {
        List<Appointment> allAppointments = appointmentDao.findAll();

        return allAppointments.stream()
                .map( appointment -> mapper.convertValue( appointment , AppointmentDto.class ) )
                .collect( Collectors.toSet() );
    }

    @Override
    public void add( AppointmentDto appointmentDto ) {
        Appointment appointment = convertDto( appointmentDto );
        appointmentDao.save( appointment );
    }

    @Override
    public AppointmentDto update( Long id, AppointmentDto appointment ) {
        Optional< Appointment > appointmentFromDb = appointmentDao.findById( id );

        if( appointmentFromDb.isPresent() ) {
            Appointment appointmentConverted = convertDto( appointment );
            appointmentConverted.setId( id );
            appointmentConverted = appointmentDao.save( appointmentConverted );

            return mapper.convertValue( appointmentConverted , AppointmentDto.class );
        }

        throw new RuntimeException( "Null on find appointment by id: " + id );
    }

    @Override
    public void delete( Long id ) {
        if( find(id) != null )
            appointmentDao.deleteById( id );
    }

    @Override
    public AppointmentDto find( Long id ) {
        Optional< Appointment > appointment = appointmentDao.findById( id );
        AppointmentDto appointmentDto;

        if ( appointment.isPresent() ) {
            appointmentDto = mapper.convertValue( appointment , AppointmentDto.class );
            return appointmentDto;
        }

        throw new RuntimeException( "Null on find appointment by id: " + id );
    }

    @Override
    public Appointment convertDto( AppointmentDto appointmentDto ) {
        return mapper.convertValue( appointmentDto, Appointment.class );
    }

}
