package ar.com.digitalhouse.ctd.clinicproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIgnoreProperties( ignoreUnknown = true )
@Setter
@Getter
public class AppointmentDto {

    private PatientDto patient;
    private DentistDto dentist;
    private LocalDateTime dateTime;

}
