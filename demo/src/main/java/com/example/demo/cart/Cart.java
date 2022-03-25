
package com.example.demo.cart;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;

public class Cart {
    @OneToOne
    @JoinColumn(
        name = "user_id", 
        referencedColumnName = "id"
        )
    private User user;
    private int salesNumber;
    private String title;
    ArrayList<String> bookList = new ArrayList<>();
    public Cart(){}
    public Cart(String user){
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
    public ArrayList<String> getList(){
        return bookList;
    }    
    
    public void removeBook(Book name){
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
