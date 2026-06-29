package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.entity.Ride;
import org.example.factory.model.Converter;
import org.example.factory.model.RideModel;
import org.example.factory.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/ride")
@RestController
@Slf4j
public class RideController {

    private final RideService rideService;

    private final Converter converter;

    public RideController(RideService rideService, Converter converter) {
        this.converter = converter;
        this.rideService = rideService;
    }

    //microservice
    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        try {
            rideService.createRide(ride);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ride);
        }
    }

    @GetMapping
    public ResponseEntity<List<RideModel>> getRides() {
        try {
            List<Ride> rides = rideService.getAllTravels();
            List<RideModel> rideModels = rides.stream()
                    .map(converter::convertRideModelToEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(rideModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/id")
    public ResponseEntity<RideModel> getId(@RequestParam Long id) {
        try {
            Ride ride = rideService.getTravelById(id);
            RideModel rideModel = converter.convertRideModelToEntity(ride);
            return ResponseEntity.ok().body(rideModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<RideModel> UpdateRide(@RequestBody Ride ride) {
        try {
            Ride rides = rideService.updateTravel(ride);
            return ResponseEntity.ok().body(converter.convertRideModelToEntity(rides));
        } catch (Exception e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> DeleteRide(@RequestParam Long id) {
        try {
            rideService.deleteTravel(id);
            return ResponseEntity.ok().body("ride has been deleted");
        } catch (Exception e) {
            log.error(e.getMessage());
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
