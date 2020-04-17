package model;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "users")
public class User {

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public User(Long id, String login, String email, String pass) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = pass;
    }

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password);
    }
}
