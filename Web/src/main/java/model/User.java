package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users", schema = "test")
public class User implements Serializable { // Serializable Important to Hibernate

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
            generator="users_seq")
    @SequenceGenerator(name="users_seq",
            sequenceName="SEQ_USER",
            allocationSize=5)
    @Column(name="id", updatable=false, nullable=false)
    private long id;

    @Column(name = "name", unique = true, updatable = false, nullable = false)
    private String name;

    @Column(name = "login", unique = true, updatable = false, nullable = false)
    private String login;

    @Column(name = "password", updatable = false, nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "users",
                cascade = CascadeType.ALL)
    private List<Role> roles;

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public User(long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String name, String login, String password) {
        super();
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRole() {
        return roles;
    }

    public void setRole(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + login + ", " + password  + ", " + roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, roles);
    }
}
