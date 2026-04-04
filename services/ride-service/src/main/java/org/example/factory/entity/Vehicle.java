package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "driver_id" , length = 50 , nullable = false)
    public long driverId;
    @Column(name = "license_plate" , length = 50 , nullable = false)
    public String licensePlate;
    @Column(name = "model" , nullable = false , length = 50)
    public String model;
    @Column(name = "color" , nullable = false , length = 50)
    public String color;
}
