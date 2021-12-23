package com.libproject.sc.service;

import com.libproject.sc.domain.Book;
import com.libproject.sc.domain.Borrowing;
import com.libproject.sc.domain.Category;
import com.libproject.sc.repository.BookRepository;
import com.libproject.sc.repository.BorrowingRepository;
import com.libproject.sc.repository.CategoryRepository;

import com.libproject.sc.service.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;

    public CategoryService(CategoryRepository categoryRepository, BookRepository bookRepository, BorrowingRepository borrowingRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.borrowingRepository = borrowingRepository;
    }

    public CategoryDTO getOneCategory(Long id){

        Category category = categoryRepository.findById(id).orElse(null);
        CategoryDTO currentCategoryDTO = new CategoryDTO(category);
        currentCategoryDTO.setAvailable_number(getAvailableBooks(category));
        currentCategoryDTO.setTotal_number(getTotalBooks(category));
        return currentCategoryDTO;
    }

    public Integer getAvailableBooks(Category category){
        List<Book> books = bookRepository.findByCategoryId(category);
        Integer total_unique_quantity = books.size();
        Integer borrowed_quantity = 0;
        Integer total_quantity = 0;
        for(int i = 0 ; i < total_unique_quantity; i++){
            Book currentbook = books.get(i);
            Integer original_quantity  = currentbook.getQuantity();
            List<Borrowing> borrowed = borrowingRepository.findByBookId(currentbook);
            borrowed_quantity += borrowed.size();
            total_quantity += original_quantity;
        }
        Integer available = total_quantity - borrowed_quantity;
        return  available;
    }

    public Integer getTotalBooks(Category category){
        List<Book> books = bookRepository.findByCategoryId(category);
        Integer total_unique_quantity = books.size();
        Integer total_quantity = 0;
        for(int i = 0 ; i < total_unique_quantity; i++){
            Book currentbook = books.get(i);
            total_quantity +=  currentbook.getQuantity();
        }
        return  total_quantity;
    }
}
