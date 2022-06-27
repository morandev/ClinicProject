package ar.com.digitalhouse.ctd.clinicproject.model.service;

import ar.com.digitalhouse.ctd.clinicproject.dto.AppointmentDto;
import ar.com.digitalhouse.ctd.clinicproject.dto.DentistDto;
import ar.com.digitalhouse.ctd.clinicproject.model.entity.Appointment;

import java.util.Optional;
import java.util.Set;

public interface IAppointmentService {

    Set<AppointmentDto> getAll();
    Optional<AppointmentDto> add( AppointmentDto appointment );
    Optional<AppointmentDto> update( Long id , AppointmentDto appointment );
    Optional<AppointmentDto> find( Long id );
    void delete( Long id );
    Appointment convertDto( AppointmentDto appointmentDto );
    boolean validate( AppointmentDto appointmentDto );
}
