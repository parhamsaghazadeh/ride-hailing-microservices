package org.example.factory.service;

import org.example.factory.client.UserClient;
import org.example.factory.entity.Ride;
import org.example.factory.repository.RatingRepository;
import org.example.factory.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    private RideRepository rideRepository;

    private final UserClient userClient;


    public RideService(RideRepository rideRepository ,UserClient userClient) {
        this.rideRepository = rideRepository;
        this.userClient = userClient;
    }

    public List<Ride> getAllTravels() {
        return rideRepository.findAll();
    }

    public Ride updateTravel(Ride ride) {
        return rideRepository.save(ride);
    }

    public void deleteTravel(Ride ride) {
        rideRepository.delete(ride);
    }

    public Ride getTravelById(Long id) {
        return rideRepository.getReferenceById(id);
    }

        public Ride createRide(Ride ride) {

            boolean isDriver =
                    userClient.isDriver(
                            ride.getDriverId()
                    );

            if (!isDriver) {

                throw new RuntimeException(
                        "Driver not valid"
                );
            }

            return rideRepository.save(ride);
        }
    }
