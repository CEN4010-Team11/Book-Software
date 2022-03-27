package com.example.demo.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class wishlistService {
    private ArrayList<wishlist> wishlists;

    @Autowired
    public wishlistService(ArrayList<wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public ArrayList<wishlist> getWishlists() {
        return wishlists;
    }

    public ArrayList<wishlist> getWishListByName(String name) {
        ArrayList<wishlist> wishlist = new ArrayList<wishlist>();
        for (wishlist w : wishlists) {
            if (w.getName().equals(name)) {
                wishlist.add(w);
            }
        }
        return wishlist;
    }
}
