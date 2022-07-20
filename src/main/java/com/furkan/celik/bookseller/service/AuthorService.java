package com.furkan.celik.bookseller.service;

import com.furkan.celik.bookseller.model.Author;
import com.furkan.celik.bookseller.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author furkancelik
 **/

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author saveOrUpdate(Author author) {
        try {
            Author savedAuthor = authorRepository.save(author);
            return savedAuthor;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean deleteByID(Long authorID) {
        try {
            authorRepository.deleteById(authorID);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }
}
