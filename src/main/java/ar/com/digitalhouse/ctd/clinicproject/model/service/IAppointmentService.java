package ar.com.digitalhouse.ctd.clinicproject.model.service;

import ar.com.digitalhouse.ctd.clinicproject.dto.AppointmentDto;
import ar.com.digitalhouse.ctd.clinicproject.model.entity.Appointment;

import java.util.Set;

public interface IAppointmentService {

    Set<AppointmentDto> getAll();
    void add( AppointmentDto appointment );
    AppointmentDto update( Long id , AppointmentDto appointment );
    void delete( Long id );
    AppointmentDto find( Long id );
    Appointment convertDto( AppointmentDto appointmentDto );

}
