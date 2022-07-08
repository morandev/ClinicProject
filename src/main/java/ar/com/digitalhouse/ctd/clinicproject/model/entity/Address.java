package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "addresses" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @SequenceGenerator( name = "address_seq" , sequenceName = "address_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "address_seq" )
    @Column( name = "address_id" )
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
