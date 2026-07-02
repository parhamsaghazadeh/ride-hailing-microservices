package org.example.factory.service;

import org.example.factory.client.UserClient;
import org.example.factory.entity.Ride;
import org.example.factory.repository.RatingRepository;
import org.example.factory.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RideService {

    private RideRepository rideRepository;

    private final UserClient userClient;


    public RideService(RideRepository rideRepository, UserClient userClient) {
        this.rideRepository = rideRepository;
        this.userClient = userClient;
    }

    public List<Ride> getAllTravels() {
        return rideRepository.findAll();
    }

    public Ride updateTravel(Ride ride) {
        return rideRepository.save(ride);
    }

    public void deleteTravel(Long id) {
        rideRepository.deleteById(id);
    }

    public Ride getTravelById(Long id) {
        return rideRepository.getReferenceById(id);
    }

    public Ride createRide(Ride ride) {

        boolean isDriver =
                userClient.isDriver(
                        ride.getDriverId()
                );

        boolean isTraveler = userClient.isTraveler(
                ride.getPassengerId()
        );

        if (!isDriver && !isTraveler) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Driver and Traveler not found");
        }

        if (!isDriver) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Driver not found");
        }

        if (!isTraveler) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Traveler not found");
        }

        return rideRepository.save(ride);
    }

    public Ride findRideById(Long id) {
        return rideRepository.findById(id).orElseGet(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ride not found");
        });
    }
}
