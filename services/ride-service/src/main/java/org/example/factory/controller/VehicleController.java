package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.entity.Vehicle;
import org.example.factory.model.Converter;
import org.example.factory.model.VehicleModel;
import org.example.factory.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/vehicle")
@RestController
@Slf4j
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private Converter converter;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            vehicleService.createVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(vehicle);
        }
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>>  getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleService.findAll();
            List<VehicleModel> vehicleModels = vehicles.stream()
                    .map(converter::convertVehicleModelToEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(vehicles);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
            }

}
