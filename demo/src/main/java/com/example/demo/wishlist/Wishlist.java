package com.example.demo.wishlist;

import java.util.ArrayList;

import com.example.demo.book.Book;

import java.util.*;

public class wishlist {
    private String name;
    private String user;
    ArrayList<Book> books;

    // default constructor
    public wishlist() {
        books = new ArrayList<Book>();
    }

    // parameterized constructor
    public wishlist(String name, String user, Book book) {
        this.name = name;
        this.user = user;
        this.books = new ArrayList<Book>();
        this.books.add(book);
    }

    public wishlist(String user, String name) {
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

    // get book from wishlist
    // this method will be used to get book from wishlist to the cart
    public Book getBook(Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                return b;
            }
        }
    }

    // add book into the wishlist
    public void addBook(Book book) {
        books.add(book);
    }

    // remove book from the wishlist
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public String toString() {
        return "wishlist{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", books=" + books +
                '}';
    }

}
