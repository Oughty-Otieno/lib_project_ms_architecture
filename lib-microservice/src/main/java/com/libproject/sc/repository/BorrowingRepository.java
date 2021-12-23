package com.libproject.sc.repository;

import com.libproject.sc.domain.Book;
import com.libproject.sc.domain.Borrowing;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.DoubleStream;

/**
 * Spring Data SQL repository for the Borrowing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    @Query("select borrowing from Borrowing borrowing where borrowing.book = :book and borrowing.return_date = null")
    List<Borrowing> findByBookId(@Param("book")Book book);
}
