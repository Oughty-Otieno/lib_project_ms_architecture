package com.libproject.sc.repository;

import com.libproject.sc.domain.Book;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

/**
 * Spring Data SQL repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
