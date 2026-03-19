package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ride")
@Data
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "starting_point" , nullable = false , length = 50)
    private String startingPoint;
    @Column(name = "destination" , nullable = false , length = 50)
    private String destination;
    @Column(name = "ride_time", nullable = false , length = 50)
    private LocalDateTime rideTime;
    @Column(name = "ride_price" , nullable = false ,length = 50)
    private BigDecimal ridePrice;
    @OneToOne
    @JoinColumn(name = "vehicle_id" , foreignKey = @ForeignKey(name = "FK-ride-vehicle"))
    private Vehicle vehicle;
    @Column(name = "user_id", nullable = false , length = 20)
    private long userId;
}
