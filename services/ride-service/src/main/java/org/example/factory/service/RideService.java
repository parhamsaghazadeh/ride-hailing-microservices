package org.example.factory.service;

import org.apache.catalina.User;
import org.example.factory.RestTemplate;
import org.example.factory.entity.Ride;
import org.example.factory.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    private RideRepository rideRepository;

    private RestTemplate restTemplate;

    @Autowired
    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }


    public Ride savaRide(Ride ride) {
        return rideRepository.save(ride);

        try {
            String url = "http://localhost:8080/user/" + ride.getUserId();
            
        }
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

    public Ride getTravelById(Long id){
        return rideRepository.getReferenceById(id);
    }
}
