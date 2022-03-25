package com.example.demo.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository rep;
    @Autowired
    public CartService(CartRepository rep){
        this.rep = rep;
    }
    public List<Cart> getCart(){
        return rep.findAll();
    }
    public Optional<Cart> getCartByID(Long Cartid) {
        return rep.findCartByID(Cartid);
    }
}
