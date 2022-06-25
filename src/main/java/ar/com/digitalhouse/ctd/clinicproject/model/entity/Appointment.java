package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table( name = "appointments" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @SequenceGenerator( name = "appointment_seq" , sequenceName = "appointment_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "appointment_seq" )
    @Column( name = "appointment_id" )
    private Long id;
    @ManyToOne( fetch = FetchType.LAZY , cascade = CascadeType.ALL )
    @JoinColumn( name = "fk_patient_id" , referencedColumnName = "patient_id" , nullable = false )
    private Patient patient;
    @ManyToOne( fetch = FetchType.LAZY , cascade = { CascadeType.ALL } )
    @JoinColumn( name = "fk_dentist_id" , referencedColumnName = "dentist_id" , nullable = false )
    private Dentist dentist;
    @Column
    private LocalDateTime dateTime;

}
