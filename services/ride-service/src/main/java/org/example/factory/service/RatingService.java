package org.example.factory.service;

import org.example.factory.client.UserClient;
import org.example.factory.entity.Rating;
import org.example.factory.repository.RatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RatingService {
    private RatingRepository ratingRepository;

    private UserClient userClient;

    public RatingService(RatingRepository ratingRepository, UserClient userClient) {
        this.ratingRepository = ratingRepository;
        this.userClient = userClient;
    }

    public Rating updateRating(Rating rating) {
        Rating oldRating = ratingRepository.findById(rating.getId()).orElse(null);

        oldRating.setRatingLevel(rating.getRatingLevel());
        oldRating.setScore(rating.getScore());
        oldRating.setComment(rating.getComment());
        return ratingRepository.save(oldRating);
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating findRatingById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }


    public Rating createRating(Rating rating) {

        boolean isDriver = userClient.isDriver(rating.getDriverId());

        boolean isTraveler = userClient.isTraveler(rating.getPassengerId());

        if (!isDriver && !isTraveler) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Driver and Traveler not found");
        }

        if (!isDriver) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Driver not found");
        }

        if (!isTraveler) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Traveler not found");
        }

        return ratingRepository.save(rating);

    }
}
