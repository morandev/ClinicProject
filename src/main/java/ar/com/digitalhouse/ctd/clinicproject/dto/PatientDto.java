package ar.com.digitalhouse.ctd.clinicproject.dto;

import ar.com.digitalhouse.ctd.clinicproject.model.entity.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonIgnoreProperties( ignoreUnknown = true )
@Setter
@Getter
public class PatientDto {

    private String name;
    private String surname;
    private Address address;
    private LocalDate registerDate;

}
