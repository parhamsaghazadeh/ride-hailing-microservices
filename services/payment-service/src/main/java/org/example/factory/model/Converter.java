package org.example.factory.model;

import org.example.factory.entity.Payment;
import org.example.factory.entity.Transaction;
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

    public TransactionModel convertTransactionToTransactionModel(Transaction transaction) {
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setId(transaction.getId());
        transactionModel.setPaymentId(transaction.getPayment().getId());
        transactionModel.setWalletId(transaction.getWalletId());
        transactionModel.setTransactionStatus(transaction.getTransactionStatus().name());
        transactionModel.setTransactionTime(formatter.format(transaction.getTransactionTime()));
        transactionModel.setTransactionType(transaction.getTransactionType().name());
        transactionModel.setDescription(transaction.getDescription());
        transactionModel.setAmount(decimalFormat.format(transaction.getAmount()));
        return transactionModel;
    }
}
