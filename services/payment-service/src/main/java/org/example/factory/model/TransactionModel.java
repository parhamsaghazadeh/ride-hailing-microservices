package org.example.factory.model;

import lombok.Data;

@Data
public class TransactionModel {
    private Long id;
    private Long paymentId;
    private Long walletId;
    private String amount;
    private String transactionType;
    private String transactionStatus;
    private String transactionTime;
    private String description;
}
