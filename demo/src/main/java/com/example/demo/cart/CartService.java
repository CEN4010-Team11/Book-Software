package com.example.demo.cart;

import com.example.demo.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.book.BookRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CartService {
    private final CartRepository rep;
    private final Cart cart = new Cart();
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

    public void addBook(Long Cardid){
        boolean exists = bookrep.existsById(Cardid);
        if (!exists)
        {
            throw new IllegalStateException("The book with that ID is not in the database.");
        }
        Book book = bookrep.getById(Cardid);
        list = cart.getList();
        for(int i = 0; i <= list.size(); i++){
            book = list.get(i);
            Optional<Book> bookOptional2 = bookrep.findBookByISBN(book.getISBN());
            if(book.equals(bookOptional2) == true){
                throw new IllegalStateException("The book with that ISBN is already on the cart.");
            }              
        }       
        cart.addBook(book);
    }
    public void removeBook(Long Cardid){
        boolean exists = bookrep.existsById(Cardid);
        if (!exists)
        {
            throw new IllegalStateException("The book with that ID is not in the database.");
        }
        Book book = bookrep.getById(Cardid);
        list = cart.getList();
        for(int i = 0; i <= list.size(); i++){
            book = list.get(i);
            Optional<Book> bookOptional2 = bookrep.findBookByISBN(book.getISBN());
            if(book.equals(bookOptional2.get()) == true){
               cart.removeBook(book);
                check = true;
            }
        }
        if(check == false){
            throw new IllegalStateException("The book with that ISBN is not on the cart.");
        }       
    }

}
