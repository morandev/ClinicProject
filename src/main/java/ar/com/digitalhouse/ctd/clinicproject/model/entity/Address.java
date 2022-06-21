package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "addresses" )
@Getter
@Setter
public class Address {

    //TODO: revisar la estrategia de generacion de ids - ex: primer address con id 4
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "address_id", nullable = false )
    private Long id;
    @Column
    private String street;
    @Column
    private Integer number;
    @Column
    private String areaCode;
    @Column
    private String city;
    @Column
    private String province;

}
