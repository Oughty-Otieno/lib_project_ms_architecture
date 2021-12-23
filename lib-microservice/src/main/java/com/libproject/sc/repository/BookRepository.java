package com.libproject.sc.repository;

import com.libproject.sc.domain.Book;
import com.libproject.sc.domain.Borrowing;
import com.libproject.sc.domain.Category;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.DoubleStream;

/**
 * Spring Data SQL repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select book from Book book where book.category = :category")
    List<Book> findByCategoryId(@Param("category") Category category);
}
