package com.example.demo.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class WishlistService {
    private ArrayList<Wishlist> wishlists;

    @Autowired
    public WishlistService(ArrayList<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public ArrayList<Wishlist> getWishlists() {
        return wishlists;
    }

    public ArrayList<Wishlist> getWishListByName(String name) {
        ArrayList<Wishlist> wishlist = new ArrayList<Wishlist>();
        for (Wishlist w : wishlists) {
            if (w.getName().equals(name)) {
                wishlist.add(w);
            }
        }
        return wishlist;
    }
}
