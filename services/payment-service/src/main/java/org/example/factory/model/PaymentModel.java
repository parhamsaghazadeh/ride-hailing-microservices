package org.example.factory.model;

import lombok.Data;

@Data
public class PaymentModel {
    private long id;
    private long passengerId;
    private long driverId;
    private long riderId;
    private String amount;
    private String paymentStatus;
    private String paymentMethod;
    private String paymentTime;
}
