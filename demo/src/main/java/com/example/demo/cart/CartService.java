package com.example.demo.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.book.BookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CartService {
    private final CartRepository rep;
    private final Cart cart;
    private final BookRepository bookrep;
    private ArrayList<Book> list = new ArrayList<>();
    private Book book;
    private boolean check = false;
    
    @Autowired
    public CartService(CartRepository rep, BookRepository bookrep){
        this.rep = rep;
        this.bookrep = bookrep;
    }
    public List<Cart> getCart(){
        return rep.findAll();
    }
    public Optional<Cart> getCartByID(Long Cartid) {
        return rep.findCartByID(Cartid);
    }
    public void addBook(Book title){
        
        Optional<Book> bookOptional = bookrep.findBookByISBN(title.getISBN());
        if (!bookOptional.isPresent())
        {
            throw new IllegalStateException("The book with that ISBN is not in the database.");
        }
        list = cart.getList;
        for(int i = 0; i <= list.length; i++){
            book = list[i];
            Optional<Book> bookOptional2 = bookrep.findBookByISBN(book.getISBN());
            if(bookOptional.equals(bookOptional2) == true){
                throw new IllegalStateException("The book with that ISBN is already on the cart.");
            }              
        }       
        cart.addBook(title);
    }
    public void removeBook(Book name){
        Optional<Book> bookOptional = bookrep.findBookByISBN(name.getISBN());
        if (!bookOptional.isPresent())
        {
            throw new IllegalStateException("The book with that ISBN is not in the database.");
        }
        list = cart.getList();
        for(int i = 0; i <= list.length; i++){
            book = list[i];
            Optional<Book> bookOptional2 = bookrep.findBookByISBN(book.getISBN());
            if(bookOptional.equals(bookOptional2) == true){
               cart.removeBook(name);
                check = true;
            }
                
        }
        if(check == false){
            throw new IllegalStateException("The book with that ISBN is not on the cart.");
        }       
    }
    public void printList(){
        list = cart.getList();
        if(list.isEmpty == true){
            throw new IllegalStateException("There isn't any books on the cart.");
        }
        cart.printList();
    }
}
