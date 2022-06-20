package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "addresses" )
@Getter
@Setter
public class Address {

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
    private String town;
    @Column
    private String city;
    @Column
    private String department;
    @Column
    private String province;

}
