package ar.com.digitalhouse.ctd.clinicproject.model.service.impl;

import ar.com.digitalhouse.ctd.clinicproject.dto.AppointmentDto;
import ar.com.digitalhouse.ctd.clinicproject.model.entity.Appointment;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IAppointmentService;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IDentistService;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IPatientService;
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

    private final IAppointmentRepository appointmentDao;
    private final ObjectMapper mapper;
    private final IDentistService dentistService;
    private final IPatientService patientService;

    @Autowired
    public AppointmentService( IAppointmentRepository appointmentDao, ObjectMapper mapper, IDentistService dentistService, IPatientService patientService  ) {
        this.appointmentDao = appointmentDao;
        this.mapper = mapper;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }
    @Override
    public Set<AppointmentDto> getAll() {
        List<Appointment> allAppointments = appointmentDao.findAll();

        return allAppointments.stream()
                .map( appointment -> mapper.convertValue( appointment , AppointmentDto.class ) )
                .collect( Collectors.toSet() );
    }

    @Override
    public Optional<AppointmentDto> add( AppointmentDto appointmentDto ) {
        Appointment appointment = convertDto( appointmentDto );
        Optional<AppointmentDto> dto = Optional.of( mapper.convertValue( appointmentDao.save( appointment ), AppointmentDto.class ) );
        return dto;
    }

    @Override
    public Optional<AppointmentDto> update( Long id , AppointmentDto appointment ) {

        Optional<Appointment> appointmentFromDb = appointmentDao.findById( id );
        Optional<AppointmentDto> appointmentDto = Optional.empty();

        if( appointmentFromDb.isPresent() ) {

            Appointment appointmentConverted = mapper.convertValue( appointment , Appointment.class );
            appointmentConverted.setId( id );
            appointmentConverted = appointmentDao.save( appointmentConverted );
            appointmentDto = Optional.of( mapper.convertValue( appointmentConverted , AppointmentDto.class ) );

            return appointmentDto;
        }

        return appointmentDto;
    }

    @Override
    public void delete( Long id ) {
        if( find(id) != null )
            appointmentDao.deleteById( id );
    }

    @Override
    public Optional<AppointmentDto> find( Long id ) {
        Optional<Appointment> appointment = appointmentDao.findById( id );
        Optional<AppointmentDto> appointmentDto = Optional.empty();

        if ( appointment.isPresent() )
            appointmentDto = Optional.of( mapper.convertValue( appointment , AppointmentDto.class ) );

        return appointmentDto;
    }

    @Override
    public Appointment convertDto( AppointmentDto appointmentDto ) {
        return mapper.convertValue( appointmentDto , Appointment.class );
    }

    @Override
    public boolean validate(AppointmentDto appointmentDto) {
        return patientService.validate( appointmentDto.getPatient() )
                                        &&
                dentistService.validate( appointmentDto.getDentist() );
    }


}
