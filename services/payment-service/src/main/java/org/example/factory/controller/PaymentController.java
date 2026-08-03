package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.entity.Payment;
import org.example.factory.model.Converter;
import org.example.factory.model.PaymentModel;
import org.example.factory.model.RideModel;
import org.example.factory.service.PaymentService;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/payment")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private Converter converter;

    @PostMapping
    public ResponseEntity<PaymentModel> createPayment(@RequestBody Payment payment) {
        try {
            Payment payments = paymentService.createPayment(payment);
            PaymentModel paymentModel = converter.convertPaymentToPaymentModel(payments);
            return ResponseEntity.ok(paymentModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentModel>> getAllPayments() {
        try {
            List<Payment> payments = paymentService.getPaymentList();
            List<PaymentModel> paymentModels = payments.stream().map(converter::convertPaymentToPaymentModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(paymentModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<PaymentModel> getPaymentById(@RequestParam Long id) {
        try {
            Payment payments = paymentService.getPaymentById(id);
            PaymentModel paymentModel = converter.convertPaymentToPaymentModel(payments);
            return ResponseEntity.ok(paymentModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping
    public ResponseEntity<PaymentModel> updatePayment(@RequestBody Payment payment) {
        try {
            Payment payments = paymentService.updatePayment(payment);
            PaymentModel paymentModel = converter.convertPaymentToPaymentModel(payments);
            return ResponseEntity.ok(paymentModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public void deletePayment(@RequestParam Long id) {
        try {
            paymentService.deletePaymentById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
