package ar.com.digitalhouse.ctd.clinicproject.model.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table( name = "users" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator( name = "user_seq" , sequenceName = "common_sequence" )
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "user_seq" )
    @Column( name = "user_id" , nullable = false )
    private Long id;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String username;
    @Column( unique = true )
    private String email;
    @Column
    private String password;
    @Column
    @Enumerated( EnumType.STRING )
    private Rol rol;

}
