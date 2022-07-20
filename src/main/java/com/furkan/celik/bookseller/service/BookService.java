package com.furkan.celik.bookseller.service;

import com.furkan.celik.bookseller.model.Book;
import com.furkan.celik.bookseller.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author furkancelik
 **/

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book saveOrUpdate(Book book) {
        try {
            Book savedBook = bookRepository.save(book);
            return savedBook;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
