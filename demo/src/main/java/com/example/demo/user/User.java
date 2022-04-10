package com.example.demo.user;
import com.example.demo.cart.Cart;
import com.example.demo.wishlist.Wishlist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BookstoreUser")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", updatable = false, nullable = false)
    String email;

    @Column(name = "password", updatable = true, nullable = false)
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "address")
    String address;

    /*
    user relationships with cart and wishlists, will uncomment later
     */

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.ALL})
//    private List<Wishlist> wishlists = new ArrayList<>();
//
//    @OneToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(
//            name = "cart_id",
//            referencedColumnName = "id"
//    )
    private Cart cart;


    public User() {
    }

    public User(String email, String password, String name, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void createCart(){
        Cart newCart = new Cart();
        this.setCart(newCart);
        newCart.setUser(this);
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    //include cart and wishlists when ready


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cart=" + cart +
                '}';
    }
}
