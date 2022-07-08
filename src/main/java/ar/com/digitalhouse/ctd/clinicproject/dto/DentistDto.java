package ar.com.digitalhouse.ctd.clinicproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonPropertyOrder( alphabetic = true )
@Setter
@Getter
public class DentistDto {

    private long id;
    @NotEmpty( message = "Please enter name" )
    private String name;
    @NotEmpty( message = "Please enter surname" )
    private String surname;
    @NotEmpty( message = "Please enter enrollment" )
    private String enrollment;

}
