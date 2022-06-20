package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name = "dentists" )
@Getter
@Setter
public class Dentist {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "dentist_id", nullable = false )
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String enrollment;

}
