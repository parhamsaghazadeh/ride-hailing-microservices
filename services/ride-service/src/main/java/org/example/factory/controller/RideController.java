package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.entity.Ride;
import org.example.factory.service.RideService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/rating")
@RestController
@Slf4j
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping
    public Ride createRide(
            @RequestBody Ride ride
    ) {

        return rideService.createRide(ride);
    }
}
