package by.evgen.Cafe.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Table(name = "cafe")
public class CafeUserModel implements CafeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    private RoleType role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeUserModel that = (CafeUserModel) o;
        return Objects.equals(id, that.id)
                && Objects.equals(login, that.login)
                && Objects.equals(password, that.password)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role);
    }

    @Override
    public String toString() {
        return "CafeUserModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
