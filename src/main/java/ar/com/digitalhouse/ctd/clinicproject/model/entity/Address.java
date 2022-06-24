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
    @SequenceGenerator( name = "address_seq" , sequenceName = "common_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "address_seq" )
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
