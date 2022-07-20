package com.furkan.celik.bookseller.controller;

import com.furkan.celik.bookseller.model.BookRequisition;
import com.furkan.celik.bookseller.repository.BookRequisitionRepository;
import com.furkan.celik.bookseller.service.BookRequisitionService;
import com.furkan.celik.bookseller.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author furkancelik
 **/

@RestController
@RequestMapping(value = "/requisition")
@RequiredArgsConstructor
public class BookRequisitionController {

    private final BookRequisitionService bookRequisitionService;
    private final BookRequisitionRepository bookRequisitionRepository;

    @PostMapping(value = "/save")
    public HashMap<String, Object> saveOrUpdate(@RequestBody BookRequisition bookRequisition){

        Object save = bookRequisitionService.save(bookRequisition);
        Boolean success = false;
        String message = "Creation is failed";
        if (save != null){
            success = save.getClass() == BookRequisition.class;
            message = success ? "Creation is success" : (String) save;
        }
        return new CustomResponse(success, success ? save : null, message).toHashMap();
    }

    @GetMapping(value = "/all")
    public HashMap<String, Object> getAll(){
        List<BookRequisition> requisitionList = new ArrayList<>();
        try {
            requisitionList = bookRequisitionRepository.findAll();
            return new CustomResponse(true, requisitionList, "Operation is success").toHashMap();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CustomResponse(false, requisitionList, "Operaion is failed").toHashMap();
        }
    }

    @PostMapping(value = "/delete")
    public HashMap<String, Object> deleteById(@RequestParam Long requisitionID){
        Boolean success = bookRequisitionService.deleteByID(requisitionID);
        String message = success ? "Delete is success" : "Delete is failed";
        return new CustomResponse(success, null, message).toHashMap();
    }

    @GetMapping(value = "/report/{sellerId}/{date}")
    public HashMap<String, Object> reportBySellerAndDate(@PathVariable("sellerId") Long sellerID,
                                                         @PathVariable("date") Date date){
        List<BookRequisition> requisitionList = bookRequisitionService.getBySellerAndDate(sellerID, date);
        Boolean success = requisitionList != null;
        String message = success ? "Requisition report" : "Something went wrong";
        return new CustomResponse(success, requisitionList, message).toHashMap();

    }
}
