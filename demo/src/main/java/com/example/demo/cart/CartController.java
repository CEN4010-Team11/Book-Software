package com.example.demo.cart;

import com.example.demo.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/cart")

public class CartController {
    private final CartService service;
    @Autowired
    public CartController(CartService service){
        this.service = service;
    }

    @GetMapping
    public List<Cart> getCart(){
        return service.getCart();
    }

    @GetMapping("{Cartid}")
    public Optional<Cart> getCartByID(@PathVariable Long Cartid){
        return service.getCartByID(Cartid);
    }

    @GetMapping("{Cartid}/list")
    public List<String> getCartListByID(@PathVariable Long Cartid){
        return service.getCartListByID(Cartid);
    }

    @PostMapping("{Cartid}/{bookName}")
    public void addBook(@PathVariable Long Cartid, @PathVariable String bookName){
        service.addBook(Cartid, bookName);
    }

    @DeleteMapping("{Cartid}/{bookName}")
    public void removeBook(@PathVariable Long Cartid, @PathVariable String bookName){
        service.removeBook(Cartid, bookName);
    }

    @PostMapping("{userID}")
    public void addCart(@PathVariable Long userID){
        service.addCart(userID);
    }


}
