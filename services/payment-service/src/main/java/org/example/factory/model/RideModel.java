package org.example.factory.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
//connection to the ride service
public class RideModel {
    private Long id;
    private Long driverId;
    private Long passengerId;
    private BigDecimal ridePrice;
}
