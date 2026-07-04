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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/payment")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private Converter converter;

    @PostMapping
    public ResponseEntity<PaymentModel> createRide(@RequestBody Payment payment) {
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
}
