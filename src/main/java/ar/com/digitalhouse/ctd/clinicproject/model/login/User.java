package ar.com.digitalhouse.ctd.clinicproject.model.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity( name = "users_entity" )
@Table( name = "users" )
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "user_id", nullable = false )
    private Long id;
    @Basic
    @Column
    private String name;
    @Basic
    @Column
    private String lastName;
    @Basic
    @Column
    private String username;
    @Basic
    @Column( unique = true )
    private String email;
    @Basic
    @Column
    private String password;
    @Basic
    @Column
    @Enumerated( EnumType.STRING )
    private Rol rol;

}
