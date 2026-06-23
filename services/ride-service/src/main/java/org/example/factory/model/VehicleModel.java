package org.example.factory.model;

import lombok.Data;

@Data
public class VehicleModel {
    private long id;
    private long driverId;
    private String licensePlate;
    private String model;
    private String color;
}
