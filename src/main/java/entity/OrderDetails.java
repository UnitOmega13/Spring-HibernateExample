package entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import java.util.Objects;

@Entity
@Table(name = "order")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "basketID")
    private Basket basket;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "digitalCodeID")
    private DigitalCode digitalCode;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 14, nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    public OrderDetails() {
    }

    public OrderDetails(Basket basket, User user, DigitalCode digitalCode, String email,
                        String phoneNumber, String address) {
        this.basket = basket;
        this.user = user;
        this.digitalCode = digitalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DigitalCode getDigitalCode() {
        return digitalCode;
    }

    public void setDigitalCode(DigitalCode digitalCode) {
        this.digitalCode = digitalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(basket, that.basket) &&
                Objects.equals(user, that.user) &&
                Objects.equals(digitalCode, that.digitalCode) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basket, user, digitalCode, email, phoneNumber, address);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", basket=" + basket +
                ", user=" + user +
                ", digitalCode=" + digitalCode +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
