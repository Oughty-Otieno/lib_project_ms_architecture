package com.libproject.sc.service;

import com.libproject.sc.domain.Book;
import com.libproject.sc.domain.Borrowing;
import com.libproject.sc.domain.Category;
import com.libproject.sc.repository.BorrowingRepository;

import com.libproject.sc.service.dto.BookDTO;
import com.libproject.sc.service.dto.BorrowingDTO;
import com.libproject.sc.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;

    public BorrowingService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public Page<BorrowingDTO> getAllBorrowings(Pageable pageable){
        Page<Borrowing> borrowingsPage = borrowingRepository.findAll(pageable);

        return borrowingsPage.map(this::toBorrowingDTO);
    }

    public BorrowingDTO getBorrowing(Long id){

        Borrowing borrowing = borrowingRepository.findById(id).orElse(null);
        BorrowingDTO currentBorrowingDTO = new BorrowingDTO(borrowing);
        currentBorrowingDTO.setFine_accrued(100);


        return currentBorrowingDTO;
    }

    public BorrowingDTO toBorrowingDTO(Borrowing borrowing){
        BorrowingDTO currentBorrowDTO = new BorrowingDTO(borrowing);

        currentBorrowDTO.setFine_accrued(10);

        return currentBorrowDTO;
    }
}
