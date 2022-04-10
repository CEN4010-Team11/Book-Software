package com.example.demo.cart;

import com.example.demo.book.Book;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
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
    private final UserRepository userRep;
    private final Cart cart = new Cart();
    private ArrayList<String> list = new ArrayList<>();
    private Book book;
    
    @Autowired
    public CartService(CartRepository rep, UserRepository userRep){
        this.rep = rep;
        this.userRep = userRep;
    }
    public List<Cart> getCart(){
        return rep.findAll();
    }

    public Optional<Cart> getCartByID(Long Cartid) {
        return rep.findCartByID(Cartid);
    }

    @Transactional
    public void addBook(Long Cartid, String bookName){
//        boolean exists = bookrep.existsById(Cardid);
//        if (!exists)
//        {
//            throw new IllegalStateException("The book with that ID is not in the database.");
//        }
//        Book book = bookrep.getById(Cardid);
//        list = cart.getList();
//        for(int i = 0; i <= list.size(); i++){
//            book = list.get(i);
//            Optional<Book> bookOptional2 = bookrep.findBookByISBN(book.getISBN());
//            if(book.equals(bookOptional2) == true){
//                throw new IllegalStateException("The book with that ISBN is already on the cart.");
//            }
//        }
//        cart.addBook(book);
        if (!rep.existsById(Cartid)){
            throw new IllegalStateException("This cart does not exist in the database.");
        }
        Cart thisCart = rep.getById(Cartid);
        List<String> bookList = thisCart.getList();
        bookList.add(bookName);
    }

    @Transactional
    public void removeBook(Long Cartid, String bookName){
        boolean check = false;
        if (!rep.existsById(Cartid)){
            throw new IllegalStateException("This cart does not exist in the database.");
        }
        Cart thisCart = rep.getById(Cartid);
        List<String> bookList = thisCart.getList();
        for(int i = 0; i < bookList.size(); i++){
            String thisBook = bookList.get(i);
            if(thisBook.equals(bookName) == true){
               thisCart.removeBook(i);
               check = true;
            }
        }
        if(check == false){
            throw new IllegalStateException("The book with that ID is not on the cart.");
        }
    }

    public List<String> getCartListByID(Long Cartid) {
        if (!rep.existsById(Cartid)){
            throw new IllegalStateException("This cart does not exist in the database.");
        }
        Cart thisCart = rep.getById(Cartid);
        return thisCart.getList();
    }

    public void addCart(Long userID) {
//        for (Long i = 0L; i < rep.countByID(); i++){
//            if (rep.findUserIDByID(i).equals(userID)){
//                throw new IllegalStateException("This user already has a cart.");
//            }
//        }
        List<Cart> optionalCart = rep.findByUser_ID(userID);
        if(!optionalCart.isEmpty())
        {
            throw new IllegalStateException("This user already has a cart.");
        }
        User u = userRep.getById(userID);
        Cart newCart = new Cart(u);
        rep.save(newCart);
    }
}
