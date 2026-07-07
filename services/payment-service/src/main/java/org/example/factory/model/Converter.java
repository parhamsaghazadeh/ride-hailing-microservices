package org.example.factory.model;

import org.example.factory.entity.Payment;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

@Component
public class Converter {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public PaymentModel convertPaymentToPaymentModel(Payment payment) {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setId(payment.getId());
        paymentModel.setPassengerId(payment.getPassengerId());
        paymentModel.setDriverId(payment.getDriverId());
        paymentModel.setRiderId(payment.getRideId());
        paymentModel.setRidePrise(decimalFormat.format(payment.getRidePrice()));
        paymentModel.setPaymentStatus(payment.getPaymentStatus().name());
        paymentModel.setPaymentMethod(payment.getPaymentMethod().name());
        paymentModel.setPaymentTime(formatter.format(payment.getPaymentTime()));
        return paymentModel;
    }
}
