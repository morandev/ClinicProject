package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( name = "patients" )
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "patient_id", nullable = false )
    private Long id;
    @Basic
    @Column
    private String name;
    @Basic
    @Column
    private String surname;
    @OneToOne
    @JoinColumn( name = "fk_address_id" , referencedColumnName = "address_id" )
    private Address address;
    @Basic
    @Column
    private String dni;
    @Basic
    @Column
    private LocalDate registerDate;
}
