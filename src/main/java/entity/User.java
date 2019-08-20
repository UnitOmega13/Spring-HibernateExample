package entity;

import utils.DigitalCodeGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "code")
    private String code;

    @Column(name = "accessRole")
    private String accessRole;

    @Column (name = "salt")
    private String salt;

    public User () {}

    public User(String email, String password, String accessRole) {
        this.email = email;
        this.password = password;
        this.accessRole = accessRole;
    }

    public User(Long id, String email, String password, String accessRole) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.accessRole = accessRole;
    }

    public User(long id, String email, String login, String password, String role) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.accessRole = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode() {
        this.code = DigitalCodeGenerator.generateCode();
    }

    public String getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(String accessRole) {
        this.accessRole = accessRole;
    }
}
