package com.example.demo.wishlist;

import com.example.demo.book.Book;
import com.example.demo.user.User;
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

    // add new wishlist
    public void addNewWishlist(Wishlist wishlist) {
        wishlists.add(wishlist);
    }

    // delete wishlist
    public void deleteWishlist(String name) {
        for (Wishlist w : wishlists) {
            if (w.getName() == name) {
                wishlists.remove(w);
                break;
            }
        }
    }

    // get wishlist by user
    public ArrayList<Wishlist> getWishlistByUser(User user) {
        ArrayList<Wishlist> wishlist = new ArrayList<Wishlist>();
        for (Wishlist w : wishlists) {
            if (w.user == user) {
                wishlist.add(w);
            }
        }
        return wishlist;
    }

    // add book to wishlist
    public void addBookToWishlist(Book book, Wishlist wishlist) {
        wishlist.addBook(book);
    }

    // delete book from wishlist
    public void deleteBookFromWishlist(Book book, Wishlist wishlist) {
        wishlist.removeBook(book);
    }
}
