package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.entity.Rating;
import org.example.factory.model.Converter;
import org.example.factory.model.RatingModel;
import org.example.factory.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/rating")
@Slf4j
public class RatingController {
    @Autowired
    private RatingService ratingService;

    private Converter converter;

    //microservice
    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        try {
            ratingService.createRating(rating);
            return ResponseEntity.ok().body(rating);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping
    public ResponseEntity<List<RatingModel>> getAllRatings() {
        try {
            List<Rating> ratings = ratingService.getAllRatings();
            List<RatingModel> ratingModels = ratings.stream()
                    .map(converter::convertRatingModelToEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(ratingModels);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/id")
    public ResponseEntity<RatingModel> getRatingById(@RequestParam long id) {
        try {
            Rating rating = ratingService.findRatingById(id);
            return ResponseEntity.ok().body(converter.convertRatingModelToEntity(rating));
        }
    }

}
