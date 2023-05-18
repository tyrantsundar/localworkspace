package com.ecom.repository;

import com.ecom.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book,Long> {
}
