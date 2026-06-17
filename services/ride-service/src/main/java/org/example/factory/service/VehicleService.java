package org.example.factory.service;

import org.example.factory.client.UserClient;
import org.example.factory.entity.Vehicle;
import org.example.factory.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehicleService {
    public VehicleRepository vehicleRepository;

    public UserClient userClient;

    public VehicleService(VehicleRepository vehicleRepository , UserClient userClient) {
        this.vehicleRepository = vehicleRepository;
        this.userClient = userClient;
    }
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAll() {
        return null;
    }

    public void deleteById(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle update(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle createVehicle(Vehicle vehicle) {

        boolean isDriver = userClient.isDriver(vehicle.getDriverId());

        if (!isDriver) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Driver Id is invalid");
        }

        return vehicleRepository.save(vehicle);
    }

}
