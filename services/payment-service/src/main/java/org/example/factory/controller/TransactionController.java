package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.entity.Transaction;
import org.example.factory.model.Converter;
import org.example.factory.model.RideModel;
import org.example.factory.model.TransactionModel;
import org.example.factory.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private Converter converter;

    @PostMapping
    public ResponseEntity<TransactionModel> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction transaction1 = transactionService.createTransaction(transaction);
            TransactionModel transactionModel = converter.convertTransactionToTransactionModel(transaction1);
            return ResponseEntity.ok(transactionModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
