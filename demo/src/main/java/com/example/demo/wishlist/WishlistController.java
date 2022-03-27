package com.example.demo.wishlist;

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
    public void addWishlist(@RequestBody Wishlist wishlist) {
//        wishlistService.addNewWishlist(wishlist);
    }

    @DeleteMapping(path = "{wishlist_id}")
    public void deleteWishlist(@PathVariable("wishlist_id") Long wishlistId) {
//        wishlistService.deleteWishlist(wishlistId);
    }

    @PutMapping(path = "{wishlist_id}")
    public void updateWishlist(
            @PathVariable("wishlist_id") Long wishlistId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
//        wishlistService.updateWishlist(wishlistId, name, description);
    }

//    @GetMapping("/user/{user_id}")
//    public List<Wishlist> getWishlistByUser(@PathVariable Long user_id) {
//        return wishlistService.getWishlistByUser(user_id);
//    }
}
