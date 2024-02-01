package com.api.book.bootrestbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entity.Book;

@Service
public class BookService {

    // private static List<Book> list = new ArrayList<>();

    // static {
    // list.add(new Book(12, "Spring Boot", "Ranga Karanam"));
    // list.add(new Book(13, "Spring Boot 2", "Ranga Karanam"));
    // list.add(new Book(14, "Java Complete Reference", "Shiva"));
    // }

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    // get single book by id
    public Book getBookById(int id) {

        try {
            // book = list.stream().filter(e -> e.getBookId() == id).findFirst().get();

            Optional<Book> b1 = this.bookRepository.findById(id);
            if (b1.isPresent()) {
                return b1.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // get single
    public Book addBook(Book book) {
        // list.add(book);
        Book result = bookRepository.save(book);
        return result;
    }

    public void deleteBook(int id) {
        // list = list.stream().filter(book -> book.getBookId() !=
        // id).collect(Collectors.toList());
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book, int id) {

        // list = list.stream().map(b -> {
        // if (b.getBookId() == id) {
        // b.setTitle(book.getTitle());
        // b.setAuthor(book.getAuthor());
        // }
        // return b;
        // }).collect(Collectors.toList());
        book.setBookId(id);
        bookRepository.save(book);
        return book;
    }
}
