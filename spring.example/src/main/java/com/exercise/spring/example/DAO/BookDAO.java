package com.exercise.spring.example.DAO;

import com.exercise.spring.example.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface BookDAO extends JpaRepository<Book, Integer> {

    Book findByName(String name);
}
