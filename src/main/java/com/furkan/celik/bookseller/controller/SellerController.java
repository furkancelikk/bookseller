package com.furkan.celik.bookseller.controller;

import com.furkan.celik.bookseller.model.Seller;
import com.furkan.celik.bookseller.repository.SellerRepository;
import com.furkan.celik.bookseller.service.SellerService;
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
@RequestMapping(value = "/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final SellerRepository sellerRepository;

    @PostMapping(value = "/save")
    public HashMap<String, Object> saveOrUpdate(@RequestBody Seller seller){
        Seller savedSeller = sellerService.saveOrUpdate(seller);
        Boolean success = savedSeller != null;
        String message = success ? "Creation is success" : "Creation is failed";

        return new CustomResponse(success, savedSeller, message).toHashMap();
    }

    @GetMapping("/all")
    public HashMap<String, Object> getAll(){
        List<Seller> sellerList = new ArrayList<>();
        try {
            sellerList = sellerRepository.findAll();
            return new CustomResponse(true, sellerList, "Operation is success").toHashMap();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CustomResponse(false, sellerList, "Operaion is failed").toHashMap();
        }
    }

    @PostMapping(value = "/delete")
    public HashMap<String, Object> deleteById(@RequestParam Long sellerID){
        Boolean success = sellerService.deleteByID(sellerID);
        String message = success ? "Delete is success" : "Delete is failed";
        return new CustomResponse(success, null, message).toHashMap();
    }
}
