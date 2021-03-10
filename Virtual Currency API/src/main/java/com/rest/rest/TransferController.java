package com.rest.rest;

import com.rest.exceptions.BusinessException;
import com.rest.model.Transactions;
import com.rest.repository.TransactionsRepository;
import com.rest.service.TransferService;
import com.rest.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Muhammad Atta
 *
 */

@RestController
public class TransferController {
     private static Logger logger = LoggerFactory.getLogger(TransferController.class);
     private TransferParameter transferParameter;
     private UserServiceImpl userService;

     List<Transactions> transactions = Collections.synchronizedList(new ArrayList<>());
     TransferService transferService;

     @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    public TransferController(TransferService transferService) {
        super();
        this.transferService = transferService;
    }


    boolean flag;
    @RequestMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> transfer(@Valid @RequestBody TransferParameter  transferParameter) {
        try {
                //Calling service
                 flag = transferService.transfer(transferParameter.getFrom(), transferParameter.getTo(), transferParameter.getAmount());

                //result
            if (flag)
                    return new ResponseEntity("Transfer is successful", HttpStatus.OK);
                else
                    return new ResponseEntity("Transfer is Not successful", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (BusinessException bx) {
            return new ResponseEntity(bx.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something Went Wrong", e);
        }
    }


    //boolean flag;
    @RequestMapping(value = "/transfers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> transfers(@Valid @RequestBody TransferParameter [] transferParameter) {
        try {
            //Calling service
            List<String> response = new ArrayList<String>();
            for (TransferParameter tp: transferParameter) {
                flag = transferService.transfer(tp.getFrom(), tp.getTo(), tp.getAmount());
            }
            //result
            if (flag)
                return new ResponseEntity("Transfer is successful", HttpStatus.OK);
            else
                return new ResponseEntity("Transfer is Not successful", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (BusinessException bx) {
            return new ResponseEntity(bx.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something Went Wrong", e);
        }
    }






    //get all transfers;
    @GetMapping("/transactions")
    public List<Transactions> getAllTransactions(){
        List<Transactions> list = new ArrayList<>();
        list = transferService.findAll();
        return list;
    }







    //retrieve a list of VC transactions that sent by a user
    @RequestMapping("user/{id}/transfers_from")
    public List<Transactions> getAllTransactionsFrom(@PathVariable String id){
        return transferService.getAllTransactionsFrom(id);
    }



    //retrieve a list of VC transactions that received by a user
    @RequestMapping("user/{id}/transfers_to")
    public List<Transactions> getAllTransactionsTo(@PathVariable String id){
        return transferService.getAllTransactionsTo(id);
    }

}


