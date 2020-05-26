package com.exercise.spring.example.Service;

import com.exercise.spring.example.DAO.BookDAO;
import com.exercise.spring.example.Entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    Logger logger = LoggerFactory.getLogger(BookService.class);

    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    public Book getBookByID(int ID) {
        Optional<Book> book = bookDAO.findById(ID);
        return book.orElse(null);
    }

    public Book saveBook(Book book) {
        return bookDAO.save(book);
    }

    public Book getBookByName(String name) {
        return bookDAO.findByName(name);
    }

    public Book updateBook(Book book) {
        Book oldBook = null;
        try {
            oldBook = bookDAO.getOne(book.getID());
            oldBook.setISBN(book.getISBN());
            oldBook.setName(book.getName());
            oldBook.setPrice(book.getPrice());
        } catch (EntityNotFoundException e){
            logger.error("Something went wrong", e.getMessage());
        }
        return oldBook;
    }

    public void deleteBook(int ID) {
        bookDAO.deleteById(ID);
    }
}
