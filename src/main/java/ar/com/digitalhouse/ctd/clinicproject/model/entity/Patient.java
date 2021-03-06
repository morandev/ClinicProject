package ar.com.digitalhouse.ctd.clinicproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( name = "patients" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @SequenceGenerator( name = "patient_seq" , sequenceName = "patient_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "patient_seq" )
    @Column( name = "patient_id" )
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @OneToOne( fetch = FetchType.LAZY , cascade = CascadeType.ALL )
    @JoinColumn( name = "fk_address_id" , referencedColumnName = "address_id" )
    private Address address;
    @Column
    private String dni;
    @Column
    private LocalDate registerDate;

}
