package com.furkan.celik.bookseller.service;

import com.furkan.celik.bookseller.model.User;
import com.furkan.celik.bookseller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author furkancelik
 **/

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveOrUpdate(User user) {
        try {
            User savedUser = userRepository.save(user);
            return savedUser;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
