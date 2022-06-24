package ar.com.digitalhouse.ctd.clinicproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties( ignoreUnknown = true )
@Setter
@Getter
public class DentistDto {

    private long id;
    private String name;
    private String surname;
    private String enrollment;

}
