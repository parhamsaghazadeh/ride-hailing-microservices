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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<TransactionModel>> getAllTransactions() {
        try {
            List<Transaction> transaction = transactionService.allTransactions();
            List<TransactionModel> transactionModels = transaction.stream().map(converter::convertTransactionToTransactionModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(transactionModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("id")
    public ResponseEntity<TransactionModel> getTransactionById(@RequestParam Long id) {
        try {
            Transaction transaction = transactionService.getTransactionById(id);
            TransactionModel transactionModel = converter.convertTransactionToTransactionModel(transaction);
            return ResponseEntity.ok(transactionModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<TransactionModel> updateTransactional(@RequestBody Transaction transaction) {
        try {
            Transaction transactions = transactionService.updateTransaction(transaction);
            TransactionModel transactionModel=converter.convertTransactionToTransactionModel(transactions);
            return ResponseEntity.ok(transactionModel);
        }
        catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public void deleteTransactionById(@RequestParam Long id) {
        try {
            transactionService.deleteTransactionById(id);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
