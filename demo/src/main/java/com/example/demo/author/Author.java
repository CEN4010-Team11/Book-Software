package com.example.demo.author;

import com.example.demo.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Author")
@Table
public class Author {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "biography")
    private String biography;

    @Column(name = "publisher")
    private String publisher;

    @JsonIgnore
    @OneToMany(mappedBy = "author",
            orphanRemoval = true,
            cascade = {CascadeType.ALL}
    )
    private List<Book> booksWritten = new ArrayList<>();

    public Author() {

    }

    public Author(String first_name, String last_name, String biography, String publisher) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.biography = biography;
        this.publisher = publisher;
    }

    public Author(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Author(String first_name, String last_name, String publisher) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Book> getBooksWritten() {
        return booksWritten;
    }

    public void addBook(Book book) {
        if(!booksWritten.contains(book)){
            booksWritten.add(book);
            book.setAuthor(this);
        }
    }

    public void removeBook(Book book) {
        if(booksWritten.contains(book)) {
            booksWritten.remove(book);
            book.setAuthor(null);
        }
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", biography='" + biography + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
