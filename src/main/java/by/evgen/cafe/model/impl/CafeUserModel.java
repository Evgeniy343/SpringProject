package by.evgen.cafe.model.impl;

import by.evgen.cafe.model.CafeModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "cafe_user")
public class CafeUserModel implements CafeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "User name should not be empty")
    @Size(min = 1, max = 50, message = "User name should be between 1 and 50 characters")
    private String name;

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
    private RoleType role;

}
