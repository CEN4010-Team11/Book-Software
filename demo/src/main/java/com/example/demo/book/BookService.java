package com.example.demo.book;

import com.example.demo.author.Author;
import com.example.demo.author.AuthorRepository;
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
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findBookByISBN(book.getISBN());
        if (bookOptional.isPresent())
        {
            throw new IllegalStateException("The book with that ISBN is already in the database.");
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long book_id) {
        boolean exists = bookRepository.existsById(book_id);
        if (!exists)
        {
            throw new IllegalStateException("Book with id " + book_id + " does not exist.");
        }
        bookRepository.deleteById(book_id);
    }

    @Transactional
    public void updateBook(Long book_id, String title, String genre, String description,
                           String publisher, int year_published, double price) {
        Book book = bookRepository.findById(book_id)
                .orElseThrow(() -> new IllegalStateException("Book with id " + book_id + "does not exist."));

        //checks if title is input as argument
        if (title != null && title.length() > 0 && !Objects.equals(book.getTitle(), title)){
            book.setTitle(title);
        }

        if (genre != null && genre.length() > 0 && !Objects.equals(book.getGenre(), genre)){
            book.setGenre(genre);
        }

        if (description != null && description.length() > 0 && !Objects.equals(book.getDescription(), description)){
            book.setDescription(description);
        }

        if (publisher != null && publisher.length() > 0 && !Objects.equals(book.getPublisher(), publisher)){
            book.setPublisher(publisher);
        }

        if (year_published != 0 && !Objects.equals(book.getYear_published(), year_published)){
            book.setYear_published(year_published);
        }

        if (price != 0 && !Objects.equals(book.getPrice(), price)){
            book.setPrice(price);
        }
    }


    public List<Book> getBooksByAuthor(Long author_id) {
        List<Book> book = bookRepository.findBooksByAuthor_Id(author_id);
        return book;
    }

    public Page<Book> getTopSellers(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("sales").descending());
        return bookRepository.findAll(pageRequest);
    }

    public Optional<Book> getBookByISBN(String ISBN) {
        Optional<Book> book = bookRepository.findBookByISBN(ISBN);
        return book;
    }

    public List<Book> getBooksByGenre(String genre) {
        List<Book> book = bookRepository.findBooksByGenreContainingIgnoreCase(genre);
        return book;
    }

    public List<Book> getBooksByRating(double rating) {
        List<Book> book = bookRepository.findBooksByMinimumRating(rating);
        return book;
    }
}
