package com.libproject.sc.repository;

import com.libproject.sc.domain.Borrowing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Borrowing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {}
