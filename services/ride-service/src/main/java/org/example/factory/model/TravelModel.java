package org.example.factory.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TravelModel {
    private long id;
    private String startingPoint;
    private String destination;
    private LocalDateTime travelTime;
    private String travelPrice;
}
