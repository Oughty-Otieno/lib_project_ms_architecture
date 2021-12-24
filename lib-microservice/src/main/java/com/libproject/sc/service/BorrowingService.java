package com.libproject.sc.service;


import com.libproject.sc.domain.Borrowing;
import com.libproject.sc.repository.BorrowingRepository;
import com.libproject.sc.service.dto.BorrowingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        currentBorrowingDTO.setFine_accrued(calculateFine(borrowing));


        return currentBorrowingDTO;
    }

    public BorrowingDTO toBorrowingDTO(Borrowing borrowing){
        BorrowingDTO currentBorrowDTO = new BorrowingDTO(borrowing);

        currentBorrowDTO.setFine_accrued(calculateFine(borrowing));

        return currentBorrowDTO;
    }

    public Integer calculateFine (Borrowing borrowing){
        LocalDate due_date = borrowing.getDue_date();
        LocalDate today = LocalDate.now();
        LocalDate date_returned = borrowing.getReturn_date();
        Integer fine = borrowing.getBook().getFine_amount();
        Integer extra_days = 0;
        if(((borrowing.getReturn_date()) == null) && (today.isAfter(due_date))){
            extra_days = Math.toIntExact((ChronoUnit.DAYS.between(due_date,today)));
        } else if (((borrowing.getReturn_date()) != null) && (date_returned.isAfter(due_date))){
            extra_days = Math.toIntExact((ChronoUnit.DAYS.between(due_date,date_returned)));
        }
        return (extra_days * fine);
    }
}
