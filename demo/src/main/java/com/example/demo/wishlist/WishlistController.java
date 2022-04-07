package com.example.demo.wishlist;

import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/wishlist")

public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public List<Wishlist> getWishlists() {
        return wishlistService.getWishlists();
    }

    @PostMapping
    public void addWishlist(@RequestBody Wishlist Wishlist) {
        wishlistService.addNewWishlist(Wishlist);
    }

    @DeleteMapping(path = "{wishlist_id}")
    public void deleteWishlist(@PathVariable("wishlist_id") String wishlistId) {
        wishlistService.deleteWishlist(wishlistId);
    }

    // get Wishlist by user
    @GetMapping(path = "user/{user_id}")
    public List<Wishlist> getWishlistByUser(@PathVariable("user_id") User user) {
        return wishlistService.getWishlistByUser(user);
    }

//    // add Wishlist to cart
//    @PostMapping("/{wishlist_id}/cart/{cart_id}")
//    public void addWishlistToCart(@PathVariable Long Wishlist_id, @PathVariable Long cart_id) {
//        wishlistService.addWishlistToCart(Wishlist_id, cart_id);
//    }
//
//    // remove Wishlist from cart
//    @DeleteMapping("/{wishlist_id}/cart/{cart_id}")
//    public void removeWishlistFromCart(@PathVariable Long Wishlist_id, @PathVariable Long cart_id) {
//        wishlistService.removeWishlistFromCart(Wishlist_id, cart_id);
//    }
}
