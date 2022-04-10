package com.example.demo.cart;

import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.book.Book;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table
public class Cart {

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    @JsonIgnoreProperties({"cart", "password", "address"})
    private User user;

    @Id
    @SequenceGenerator(
            name = "cart_sequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long ID;

    private ArrayList<Book> bookList = new ArrayList<>();

    public Cart(){}

    public Cart(User user){
        this.user = user;
    }

    public void addBook(Book title){
        bookList.add(title);

    }
    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public ArrayList<Book> getList(){
        return bookList;
    }

    public void removeBook(Book name){
        bookList.remove(name);
    }
    public void purchase(){
        //incrementSales();
        bookList.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "user=" + user +
                ", ID=" + ID +
                ", bookList=" + bookList +
                '}';
    }
}
