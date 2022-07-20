package com.furkan.celik.bookseller.repository;

import com.furkan.celik.bookseller.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author furkancelik
 **/

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
