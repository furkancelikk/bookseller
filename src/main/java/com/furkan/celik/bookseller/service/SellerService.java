package com.furkan.celik.bookseller.service;

import com.furkan.celik.bookseller.model.Seller;
import com.furkan.celik.bookseller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author furkancelik
 **/

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public Seller saveOrUpdate(Seller seller) {
        try {
            Seller savedSeller = sellerRepository.save(seller);
            return savedSeller;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public Boolean deleteByID(Long sellerID) {
        try {
            sellerRepository.deleteById(sellerID);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }
}
