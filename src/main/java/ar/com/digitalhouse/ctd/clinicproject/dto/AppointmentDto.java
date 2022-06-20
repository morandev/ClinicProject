package ar.com.digitalhouse.ctd.clinicproject.dto;

import ar.com.digitalhouse.ctd.clinicproject.model.entity.Dentist;
import ar.com.digitalhouse.ctd.clinicproject.model.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIgnoreProperties( ignoreUnknown = true )
@Setter
@Getter
public class AppointmentDto {

    private Patient pacient;
    private Dentist dentist;
    private LocalDateTime dateTime;

}
