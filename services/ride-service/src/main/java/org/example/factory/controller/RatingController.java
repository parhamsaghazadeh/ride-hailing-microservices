package org.example.factory.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.factory.entity.Rating;
import org.example.factory.model.RatingModel;
import org.example.factory.service.RatingService;
import org.example.factory.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/rating")
@RestController
@Slf4j
public class RatingController {

    private RideService rideService;
    private RatingService ratingService;

    @PostMapping("/{rideId}/rate")
    public ResponseEntity<Rating> addRating(@RequestBody RatingModel ratingModel, @PathVariable Long rideId ){
        try {
            Long driverPersonId = ratingModel.getDriverId();

            Rating rating = ratingService.createRating(
                    driverPersonId,
                    ratingModel.getScore(),
                    ratingModel.getComment()
            );

            return new ResponseEntity<>(rating, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
