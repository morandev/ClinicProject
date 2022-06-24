package ar.com.digitalhouse.ctd.clinicproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonIgnoreProperties( ignoreUnknown = true )
@Setter
@Getter
public class PatientDto {

    private long id;
    private String name;
    private String surname;
    private AddressDto address;
    private LocalDate registerDate;

}
