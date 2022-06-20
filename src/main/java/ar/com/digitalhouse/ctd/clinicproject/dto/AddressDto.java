package ar.com.digitalhouse.ctd.clinicproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties( ignoreUnknown = true )
@Setter
@Getter
public class AddressDto {

    private String street;
    private Integer number;
    private String areaCode;
    private String city;
    private String province;

}
