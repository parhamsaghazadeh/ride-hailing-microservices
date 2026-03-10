package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "travel")
@Data
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "starting_point" , nullable = false , length = 50)
    private String startingPoint;
    @Column(name = "destination" , nullable = false , length = 50)
    private String destination;
    @Column(name = "travel_time", nullable = false , length = 50)
    private LocalDateTime travelTime;
    @Column(name = "travel_price" , nullable = false ,length = 50)
    private BigDecimal travelPrice;
}
