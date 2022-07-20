package com.furkan.celik.bookseller.repository;

import com.furkan.celik.bookseller.model.BookRequisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author furkancelik
 **/

@Repository
public interface BookRequisitionRepository extends JpaRepository<BookRequisition, Long> {

    @Query(value = "SELECT count(*) FROM bookseller.book_requisition where " +
            " ( " +
            "   (start_date <= :startDate and :startDate <= finish_date) or" +
            "   ( start_date <= :finishDate and :finishDate <= finish_date ) or" +
            "   ( :startDate <= start_date and start_date <= :finishDate ) or" +
            "   ( :startDate <= finish_date and finish_date <= :finishDate )" +
            ") and book_id = :bookID"
            , nativeQuery = true)
    int isBookAvailable(Date startDate, Date finishDate, Long bookID);

    @Query(value = "SELECT * FROM bookseller.book_requisition br" +
            "   join book b on br.book_id = b.id" +
            "   join seller s on s.id = b.seller_id" +
            "   where s.id = :sellerID and cast(br.created_date_time as date) = :date"
            , nativeQuery = true)
    List<BookRequisition> getBySellerIdAndCreatedDateTime(Long sellerID, LocalDateTime date);
}
