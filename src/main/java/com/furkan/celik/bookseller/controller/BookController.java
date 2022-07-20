package com.furkan.celik.bookseller.controller;

import com.furkan.celik.bookseller.model.Book;
import com.furkan.celik.bookseller.repository.BookRepository;
import com.furkan.celik.bookseller.service.BookService;
import com.furkan.celik.bookseller.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author furkancelik
 **/

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @PostMapping(value = "/save")
    public HashMap<String, Object> saveOrUpdate(@RequestBody Book book){

        Book savedBook = bookService.saveOrUpdate(book);
        Boolean success = savedBook != null;
        String message = success ? "Creation is success" : "Creation is failed";

        return new CustomResponse(success, savedBook, message).toHashMap();
    }

    @GetMapping(value = "/all")
    public HashMap<String, Object> getAll(){
        List<Book> bookList = new ArrayList<>();
        try {
            bookList = bookRepository.findAll();
            return new CustomResponse(true, bookList, "Operation is success").toHashMap();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CustomResponse(false, bookList, "Operaion is failed").toHashMap();
        }
    }
}
