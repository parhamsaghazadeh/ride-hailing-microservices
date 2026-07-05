package org.example.factory.model;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.example.factory.entity.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//Connection to the payment service
@Data
public class RideDTO {
    private int id;
    private String startingPoint;
    private String destination;
    private LocalDateTime rideTime;
    private BigDecimal ridePrice;
    private Vehicle vehicle;
    private long passengerId;
    private long driverId;
}
