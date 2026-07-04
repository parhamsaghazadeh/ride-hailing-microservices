package org.example.factory.model;

import jakarta.persistence.Column;
import org.example.factory.entity.Rating;
import org.example.factory.entity.Ride;
import org.example.factory.entity.Vehicle;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

@Component
public class Converter {

    private static final DecimalFormat DF = new  DecimalFormat("#,##0.00");

    DateTimeFormatter formatterTime =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public VehicleModel convertVehicleModelToEntity(Vehicle vehicle) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(vehicle.getId());
        vehicleModel.setModel(vehicle.getModel());
        vehicleModel.setColor(vehicle.getColor());
        vehicleModel.setDriverId(vehicle.getDriverId());
        vehicleModel.setLicensePlate(vehicle.getLicensePlate());
        return vehicleModel;
    }

    public RideModel convertRideModelToEntity(Ride ride) {
        RideModel rideModel = new RideModel();
        rideModel.setId(ride.getId());
        rideModel.setDriverId(ride.getDriverId());
        rideModel.setPassengerId(ride.getPassengerId());
        rideModel.setStartingPoint(ride.getStartingPoint());
        rideModel.setDestination(ride.getDestination());
        rideModel.setVehicleId(ride.getId());
        rideModel.setRidePrice(DF.format(ride.getRidePrice()));
        rideModel.setRideTime(formatterTime.format(ride.getRideTime()));
        return rideModel;
    }

    public RatingModel convertRatingModelToEntity(Rating rating) {
        RatingModel ratingModel = new RatingModel();
        ratingModel.setId(rating.getId());
        ratingModel.setDriverId(rating.getDriverId());
        ratingModel.setComment(rating.getComment());
        ratingModel.setScore(rating.getScore());
        ratingModel.setTimeStamp(formatterTime.format(rating.getTimeStamp()));
        ratingModel.setRatingLevel(rating.getRatingLevel().name());
        return ratingModel;
    }

    public RideDTO convertRideToDto(Ride ride) {

        RideDTO dto = new RideDTO();

        dto.setId(ride.getId());
        dto.setDriverId(ride.getDriverId());
        dto.setPassengerId(ride.getPassengerId());
        dto.setRidePrice(ride.getRidePrice());      // بدون format
        dto.setRideTime(ride.getRideTime());        // بدون format

        return dto;
    }



}
