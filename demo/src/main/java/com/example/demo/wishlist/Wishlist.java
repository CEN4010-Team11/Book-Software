package com.example.demo.wishlist;

import java.util.ArrayList;

import com.example.demo.book.Book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table
public class Wishlist {

    @Id
    private Long id;

    private String name;
    private String user;
    ArrayList<Book> books;

    // default constructor
    public Wishlist() {
        books = new ArrayList<Book>();
    }

    // parameterized constructor
    public Wishlist(String name, String user, Book book) {
        this.name = name;
        this.user = user;
        this.books = new ArrayList<Book>();
        this.books.add(book);
    }

    public Wishlist(String user, String name) {
        this.name = name;
        this.user = user;
        this.books = new ArrayList<Book>();
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
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
        books.add(book);
    }

    // remove book from the Wishlist
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", books=" + books +
                '}';
    }

}
