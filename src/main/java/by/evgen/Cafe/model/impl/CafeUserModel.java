package by.evgen.Cafe.model.impl;

import by.evgen.Cafe.model.CafeModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Parameter;

@Data
@Entity
@Table(name = "cafe_user")
public class CafeUserModel implements CafeModel {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "cafe_user_id_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    @NotEmpty(message = "Login should not be empty")
    @Size(min = 1, max = 40, message = "Login should be between 1 and 40 characters")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 1, max = 40, message = "Password should be between 1 and 40 characters")
    private String password;

    @Column(name = "role_id")
    @Enumerated(EnumType.ORDINAL)
    @NotEmpty(message = "Role should not be empty")
    private RoleType role;

    @Column(name = "name")
    @NotEmpty(message = "User name should not be empty")
    @Size(min = 1, max = 50, message = "User name should be between 1 and 50 characters")
    private String name;
}
