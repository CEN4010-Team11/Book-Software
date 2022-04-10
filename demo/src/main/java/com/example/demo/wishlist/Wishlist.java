package com.example.demo.wishlist;

import java.util.ArrayList;
import com.example.demo.book.Book;
import com.example.demo.user.User;

import javax.persistence.*;
import java.util.*;

public class Wishlist {
    @Id
    @SequenceGenerator(
            name = "wishlist_sequence",
            sequenceName = "wishlist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wishlist_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "user_wishlist_fk"
            )
    )
    User user;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH}
    )
    @JoinTable(
            name = "wishes_for",
            joinColumns = @JoinColumn(
                    name = "wishlist_id",
                    foreignKey = @ForeignKey(name = "wishes_for_wishlist_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "book_id",
                    foreignKey = @ForeignKey(name = "wishes_for_book_id_fk")
            )
    )
    List<Book> booksWished;

    // default constructor
    public Wishlist() {
        booksWished = new ArrayList<Book>();
    }

    // parameterized constructor
    public Wishlist(String name, User user, Book book) {
        this.name = name;
        this.user = user;
        this.booksWished = new ArrayList<Book>();
        this.booksWished.add(book);
    }

    public Wishlist(User user, String name) {
        this.name = name;
        this.user = user;
        this.booksWished = new ArrayList<Book>();
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    // get book from Wishlist
    // this method will be used to get book from Wishlist to the cart
//    public Book getBook(Book book) {
//        for (Book b : books) {
//            if (b.getId() == book.getId()) {
//                return b;
//            }
//        }
//    }

    // add book into the Wishlist
    public void addBook(Book book) {
        booksWished.add(book);
    }

    // remove book from the Wishlist
    public void removeBook(Book book) {
        booksWished.remove(book);
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", books=" + booksWished +
                '}';
    }

}
