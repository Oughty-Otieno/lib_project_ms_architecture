package com.libproject.sc.service.dto;

import com.libproject.sc.domain.Book;

import java.util.Optional;

public class BookDTO extends Book {

    private Integer available_quantity;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
        this.setFine_amount(book.getFine_amount());
        this.setPublisher(book.getPublisher());
        this.setQuantity(book.getQuantity());
        this.setCategory(book.getCategory());
    }


    public Integer getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(Integer available_quantity) {
        this.available_quantity = available_quantity;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
            "available_quantity=" + available_quantity +
            "} " + super.toString();
    }
}
