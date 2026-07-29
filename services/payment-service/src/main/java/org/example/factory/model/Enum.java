package org.example.factory.model;

public class Enum {
    public enum paymentStatus {
        PENDING,

        SUCCESS,

        FAILED,

        REFUNDED
    }

    public enum paymentMethod {
        CASH,

        CARD,

        WALLET
    }

    public enum transactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }

    public enum transactionStatus {
        PENDING,
        SUCCESS,
        FAILED,
    }
}
