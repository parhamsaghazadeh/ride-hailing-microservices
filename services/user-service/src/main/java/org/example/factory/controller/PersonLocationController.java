package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.Model.Converter;
import org.example.factory.Model.PersonLocationModel;
import org.example.factory.entity.PersonLocation;
import org.example.factory.service.PersonLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/location")
@Slf4j
public class PersonLocationController {
    @Autowired
    private PersonLocationService personLocationService;
    @Autowired
    private Converter converter;

    @GetMapping
    public ResponseEntity<List<PersonLocationModel>> findAll() {
        try {
            List<PersonLocation> personLocations = personLocationService.findAllLocations();
            List<PersonLocationModel> personLocationModels = personLocations.stream()
                    .map(converter::toPersonLocationModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(personLocationModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<PersonLocationModel> findById(Long id) {
        try {
            PersonLocation personLocation = personLocationService.getById(id);
            PersonLocationModel personLocationModel = converter.toPersonLocationModel(personLocation);
            return ResponseEntity.ok(personLocationModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonLocationModel> save(@RequestBody PersonLocation personLocation) {
        try {
            PersonLocation personLocation1 = personLocationService.saveLocation(personLocation);
            PersonLocationModel personLocationModel = converter.toPersonLocationModel(personLocation1);
            return ResponseEntity.ok(personLocationModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<PersonLocationModel> update(@RequestBody PersonLocation personLocation) {
        try {
            PersonLocation personLocation1 = personLocationService.updateLocation(personLocation);
            PersonLocationModel personLocationModel = converter.toPersonLocationModel(personLocation1);
            return ResponseEntity.ok(personLocationModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        try {
            PersonLocation personLocation = personLocationService.getById(id);
            return ResponseEntity.ok("delete successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
