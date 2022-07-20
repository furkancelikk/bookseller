package com.furkan.celik.bookseller.controller;

import com.furkan.celik.bookseller.model.Author;
import com.furkan.celik.bookseller.model.Book;
import com.furkan.celik.bookseller.repository.AuthorRepository;
import com.furkan.celik.bookseller.service.AuthorService;
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
@RequestMapping(value = "/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @PostMapping(value = "/save")
    public HashMap<String, Object> saveOrUpdate(@RequestBody Author author){

        Author savedAuthor = authorService.saveOrUpdate(author);
        Boolean success = savedAuthor != null;
        String message = success ? "Creation is success" : "Creation is failed";

        return new CustomResponse(success, savedAuthor, message).toHashMap();
    }

    @GetMapping(value = "/all")
    public HashMap<String, Object> getAll(){
        List<Author> authorList = new ArrayList<>();
        try {
            authorList = authorRepository.findAll();
            return new CustomResponse(true, authorList, "Operation is success").toHashMap();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CustomResponse(false, authorList, "Operaion is failed").toHashMap();
        }
    }

    @PostMapping(value = "/delete")
    public HashMap<String, Object> deleteById(@RequestParam Long authorID){
        Boolean success = authorService.deleteByID(authorID);
        String message = success ? "Delete is success" : "Delete is failed";
        return new CustomResponse(success, null, message).toHashMap();
    }
}
