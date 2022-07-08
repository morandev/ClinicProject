package ar.com.digitalhouse.ctd.clinicproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties( ignoreUnknown = true )
@JsonPropertyOrder( alphabetic = true )
@Setter
@Getter
public class UserDto {

    private long id;
    @NotEmpty( message = "Please enter name" )
    private String name;
    @NotEmpty( message = "Please enter last name" )
    private String lastName;
    @NotEmpty( message = "Please enter username" )
    private String username;
    @NotEmpty( message = "Please enter email" )
    @Email( message = "Please enter a valid email")
    private String email;
    @NotEmpty( message = "Please enter password" )
    private String password;

}
