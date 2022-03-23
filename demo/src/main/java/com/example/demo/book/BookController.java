package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks()
    {
        return bookService.getBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Book book)
    {
        bookService.addNewBook(book);
    }

    @DeleteMapping(path = "{book_id}")
    public void deleteBook(@PathVariable("book_id") Long bookId)
    {
        bookService.deleteBook(bookId);
    }

    @PutMapping(path = "{book_id}")
    public void updateBook(
            @PathVariable("book_id") Long bookId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false, defaultValue = "0") int year_published,
            @RequestParam(required = false, defaultValue = "0.00") double price)
    {
        bookService.updateBook(bookId, title, genre, description, publisher, year_published, price);
    }


    @GetMapping("/author/{author_id}")
    public List<Book> getBooksByAuthor(@PathVariable Long author_id){
        return bookService.getBooksByAuthor(author_id);
    }

    @GetMapping("/top")
    public Page<Book> getTopSellers(){
        return bookService.getTopSellers();
    }

    @GetMapping("/ISBN/{ISBN}")
    public Optional<Book> getBookByISBN(@PathVariable String ISBN) {
        return bookService.getBookByISBN(ISBN);
    }

}
