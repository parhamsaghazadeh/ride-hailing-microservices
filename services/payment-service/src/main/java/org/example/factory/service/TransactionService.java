package org.example.factory.service;

import org.example.factory.client.RideClient;
import org.example.factory.client.UserClient;
import org.example.factory.entity.Payment;
import org.example.factory.entity.Transaction;
import org.example.factory.model.PersonWalletModel;
import org.example.factory.repository.PaymentRepository;
import org.example.factory.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    private PaymentRepository paymentRepository;

    private UserClient userClient;

    public TransactionService(TransactionRepository transactionRepository, UserClient rideClient ,  PaymentRepository paymentRepository) {
        this.transactionRepository = transactionRepository;
        this.userClient = rideClient;
        this.paymentRepository = paymentRepository;
    }


    public Transaction createTransaction(Transaction transaction) {

        //اعتبار تراکنش
        if (transaction.getPayment() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Payment is null");
        }

        Payment payment = paymentRepository.findById(transaction.getPayment().getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"payment not found")
        );

        PersonWalletModel personWalletModel = userClient.getPersonWalletById(transaction.getWalletId());

        if (personWalletModel == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Wallet Not Found");
        }

        transaction.setPayment(payment);
        transaction.setAmount(payment.getRidePrice());
        transaction.setTransactionTime(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> allTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(long id) {
        return transactionRepository.findById(id).orElseGet(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"transaction not found");
        });
    }

    public void  deleteTransactionById(long id) {
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
