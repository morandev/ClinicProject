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
public class AddressDto {

    private long id;
    private String street;
    private Integer number;
    @NotEmpty( message = "Please enter area code" )
    private String areaCode;
    @NotEmpty( message = "Please enter city" )
    private String city;
    @NotEmpty( message = "Please enter province" )
    private String province;

}
