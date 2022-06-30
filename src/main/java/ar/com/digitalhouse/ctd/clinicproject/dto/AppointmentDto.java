package ar.com.digitalhouse.ctd.clinicproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonPropertyOrder( alphabetic = true )
@Setter
@Getter
public class AppointmentDto {

    private long id;
    @NotNull( message = "Please enter patient" )
    private PatientDto patient;
    @NotNull( message = "Please enter dentist" )
    private DentistDto dentist;
    @NotNull( message = "Please enter dateTime" )
    private LocalDateTime dateTime;

}
