package com.libproject.sc.service;

import com.libproject.sc.domain.Book;
import com.libproject.sc.domain.Borrowing;
import com.libproject.sc.repository.BookRepository;
import com.libproject.sc.repository.BorrowingRepository;
import com.libproject.sc.service.dto.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;

    public BookService(BookRepository bookRepository, BorrowingRepository borrowingRepository) {
        this.bookRepository = bookRepository;
        this.borrowingRepository = borrowingRepository;
    }

    public Page<BookDTO> getAll (Pageable pageable){
        List<BookDTO> bookDTOList = new ArrayList<>();
        Page<Book> bookPage = bookRepository.findAll(pageable);

//        for (int i = 0; i< bookPage.size(); i++){
//           Book current = bookPage.get(i);
//
//           bookDTOList.add(currentBookDTO);
//        }

       // return bookPage.map(this::toBookDTO);
        return  bookPage.map(b -> {
            return toBookDTO(b);
        });
    }

    public BookDTO getBook(Long id){

        Book book = bookRepository.findById(id).orElse(null);
        BookDTO currentBookDTO = new BookDTO(book);
        currentBookDTO.setAvailable_quantity(getAvailableBooks(book));


        return currentBookDTO;
    }

    public BookDTO toBookDTO(Book book){
        BookDTO currentBookDTO = new BookDTO(book);
        if(book.getQuantity() != null){
            currentBookDTO.setAvailable_quantity(getAvailableBooks(book));
        }

        return currentBookDTO;
    }

    public Integer getAvailableBooks(Book book){
        Integer original_quantity  = book.getQuantity();
        List<Borrowing> borrowed = borrowingRepository.findByBookId(book);
        Integer borrowed_quantity = borrowed.size();
        Integer available = original_quantity - borrowed_quantity;
        return  available;
    }

}
