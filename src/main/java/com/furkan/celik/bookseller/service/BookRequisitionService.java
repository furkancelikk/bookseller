package com.furkan.celik.bookseller.service;

import com.furkan.celik.bookseller.model.BookRequisition;
import com.furkan.celik.bookseller.repository.BookRequisitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author furkancelik
 **/

@Service
@RequiredArgsConstructor
public class BookRequisitionService {

    private final BookRequisitionRepository bookRequisitionRepository;

    public <T> T save(BookRequisition bookRequisition) {
        try {
            Boolean available = bookRequisitionRepository.isBookAvailable(bookRequisition.getStartDate(), bookRequisition.getFinishDate(), bookRequisition.getBook().getId()) == 0;
            if (available) {
                BookRequisition savedRequisition = bookRequisitionRepository.save(bookRequisition);
                return (T) savedRequisition;
            } else {
                return (T) "The book is not available in this date range";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public Boolean deleteByID(Long requisitionID) {
        try {
            bookRequisitionRepository.deleteById(requisitionID);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    public List<BookRequisition> getBySellerAndDate(Long sellerID, Date date) {
        try {
            LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            List<BookRequisition> requisitionList = bookRequisitionRepository.getBySellerIdAndCreatedDateTime(sellerID, localDateTime);
            return requisitionList;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
