package com.furkan.celik.bookseller.controller;

import com.furkan.celik.bookseller.model.User;
import com.furkan.celik.bookseller.repository.UserRepository;
import com.furkan.celik.bookseller.service.UserService;
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
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping(value = "/save")
    public HashMap<String, Object> saveOrUpdate(@RequestBody User user){

        User savedUser = userService.saveOrUpdate(user);
        Boolean success = savedUser != null;
        String message = success ? "Creation is success" : "Creation is failed";

        return new CustomResponse(success, savedUser, message).toHashMap();
    }

    @GetMapping(value = "/all")
    public HashMap<String, Object> getAll(){
        List<User> userList = new ArrayList<>();
        try {
            userList = userRepository.findAll();
            return new CustomResponse(true, userList, "Operation is success").toHashMap();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CustomResponse(false, userList, "Operaion is failed").toHashMap();
        }
    }
}
