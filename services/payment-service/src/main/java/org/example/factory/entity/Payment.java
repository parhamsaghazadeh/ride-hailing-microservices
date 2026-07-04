package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.factory.model.Enum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "passenger_id",nullable = false)
    private Long passengerId;
    @Column(name = "driver_id",nullable = false)
    private Long driverId;
    @Column(name = "ride_id", nullable = false)
    private Long rideId;
    @Column(name = "ride_price",nullable = false)
    private BigDecimal ridePrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status" , nullable = false)
    private Enum.paymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method" , nullable = false)
    private Enum.paymentMethod paymentMethod;
    @Column(name = "payment_time" , nullable = false)
    private LocalDateTime paymentTime;
}
