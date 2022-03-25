package com.example.demo.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Cart")

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
    @GetMapping("{CartID}")
    public Optional<Cart> getCartByID(@PathVariable Long Cartid){
        return service.getCartByID(Cartid);

    }

}
