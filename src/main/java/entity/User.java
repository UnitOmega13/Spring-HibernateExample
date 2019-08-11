package entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import utils.DigitalCodeGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "shop")
@PrimaryKeyJoinColumn(name = "id")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(getAccessRole()));
        return list;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(code, user.code) &&
                Objects.equals(accessRole, user.accessRole) &&
                Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login, password, code, accessRole, salt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", accessRole='" + accessRole + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
