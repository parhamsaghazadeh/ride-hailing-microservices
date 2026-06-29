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


    //microservice
    @PostMapping
    public ResponseEntity<VehicleModel> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            vehicleService.createVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<VehicleModel>> getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleService.findAll();
            List<VehicleModel> vehicleModels = vehicles.stream()
                    .map(converter::convertVehicleModelToEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping(value = "/id")
    public ResponseEntity<VehicleModel> getVehicleById(@RequestParam long id) {
        try {
            Vehicle vehicle = vehicleService.findById(id);
            VehicleModel vehicleModel = converter.convertVehicleModelToEntity(vehicle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<VehicleModel> updateVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle vehicleUpdate = vehicleService.update(vehicle);
            VehicleModel vehicleModel = converter.convertVehicleModelToEntity(vehicleUpdate);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVehicleById(@RequestParam long id) {
        try {
            vehicleService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
