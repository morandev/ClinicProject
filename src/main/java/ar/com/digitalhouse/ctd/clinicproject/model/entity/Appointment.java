package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name = "appointments" )
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "appointment_id", nullable = false )
    private Long id;
    @ManyToOne
    @JoinColumn( name = "fk_patient_id" , referencedColumnName = "patient_id" , nullable = false )
    private Patient patient;
    @ManyToOne
    @JoinColumn( name = "fk_dentist_id" , referencedColumnName = "dentist_id" , nullable = false )
    private Dentist dentist;
    @Column
    private LocalDateTime dateTime;

}
