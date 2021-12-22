package com.libproject.sc.service.dto;

import com.libproject.sc.domain.Borrowing;

public class BorrowingDTO extends Borrowing {
    private Integer fine_accrued;

    public BorrowingDTO(Borrowing borrowing) {
        this.setId(borrowing.getId());
        this.setDate_borrowed(borrowing.getDate_borrowed());
        this.setDue_date(borrowing.getDue_date());
        this.setReturn_date(borrowing.getReturn_date());
        this.setUser_id(borrowing.getUser_id());
        this.setBook(borrowing.getBook());
    }

    public Integer getFine_accrued() {
        return fine_accrued;
    }

    public void setFine_accrued(Integer fine_accrued) {
        this.fine_accrued = fine_accrued;
    }
}
