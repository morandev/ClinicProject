package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "dentists" )
@Getter
@Setter
public class Dentist {

    @Id
    @SequenceGenerator( name = "dentist_seq" , sequenceName = "common_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "dentist_seq" )
    @Column( name = "dentist_id" , nullable = false )
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column( unique = true )
    private String enrollment;

}
