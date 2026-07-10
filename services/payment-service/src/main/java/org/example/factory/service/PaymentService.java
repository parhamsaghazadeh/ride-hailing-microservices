package org.example.factory.service;

import org.example.factory.client.RideClient;
import org.example.factory.entity.Payment;
import org.example.factory.model.Enum;
import org.example.factory.model.RideModel;
import org.example.factory.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    private  final RideClient rideClient;
    public PaymentService(PaymentRepository paymentRepository, RideClient rideClient) {
        this.paymentRepository = paymentRepository;
        this.rideClient = rideClient;
    }

    public Payment createPayment(Payment payment){
        RideModel  rideModel = rideClient.getRideById(payment.getRideId());

        if (rideModel == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ride not found");
        }
        //دیتا هایی که از ride-service میاد
        payment.setDriverId(rideModel.getDriverId());
        payment.setPassengerId(rideModel.getPassengerId());
        payment.setRidePrice(rideModel.getRidePrice());

        payment.setPaymentTime(LocalDateTime.now());

        payment.setPaymentStatus(Enum.paymentStatus.SUCCESS);

        return paymentRepository.save(payment);
    }

}
