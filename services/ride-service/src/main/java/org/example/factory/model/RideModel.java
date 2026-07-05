package org.example.factory.model;

import lombok.Data;

@Data
//Display in output
public class RideModel {
    private long id;
    private String startingPoint;
    private String destination;
    private String rideTime;
    private String ridePrice;
    private long vehicleId;
    private long driverId;
    private long passengerId;
}
