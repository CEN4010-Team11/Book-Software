package com.example.demo.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  
  //List<Cart> printList();
    Optional<Cart> findCartByID(Long id);

    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1")
    List<Cart> findByUser_ID(Long userID);

}
