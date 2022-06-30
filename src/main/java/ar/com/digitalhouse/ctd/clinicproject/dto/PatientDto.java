package ar.com.digitalhouse.ctd.clinicproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonPropertyOrder( alphabetic = true )
@Setter
@Getter
public class PatientDto {

    private long id;
    @NotEmpty( message = "Please enter name" )
    private String name;
    @NotEmpty( message = "Please enter surname" )
    private String surname;
    @NotNull( message = "Please enter address" )
    private AddressDto address;
    @NotNull( message = "Please enter register date" )
    private LocalDate registerDate;

}
