
package com.example.demo.book;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;

public class ShoppingCart {
    private String user;
    private int salesNumber;
    //private Book book;
    private String title;
    ArrayList<String> bookList = new ArrayList<>();
    public ShoppingCart(){}
    public ShoppingCart(String user){
        this.user = user;
    }

    /* public void setBook(Book book){
        this.book = book;
    } */
    public void setTitle(String title){
        this.title = title;
        bookList.add(title);

    }
    public void remove(String name){
        bookList.remove(new String(name));
    }
    public void purchase(){
        //incrementSales();
        bookList.clear();
    }
    public void printList(){
        System.out.println("Book List:" + bookList);
    }
    @Override
    public String toString(){
        return "User" + user + "What you want to do? 1.Purchase 2.Add Book 3.Delete Book 4.Show Current Books";
    }
}
