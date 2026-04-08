package org.example.factory.service;

import org.example.factory.client.UserClient;
import org.example.factory.entity.Rating;
import org.example.factory.model.RatingModel;
import org.example.factory.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private RideRepository rideRepository;
    private UserClient userClient;

    public RatingService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public Rating createRating(Long personId, Long score , String comment) {

        if (personId == null) {
            throw new IllegalArgumentException("personId cannot be null");
        }

        if (score == null || score < 1 || score > 10) {
            throw new IllegalArgumentException("score must be between 1 and 10");
        }

        if (comment == null) {
            throw new IllegalArgumentException("comment cannot be null");
        }

        String role = userClient.getUserRole(personId);
        if (role == null || role.equals("DRIVER")){
            throw new IllegalArgumentException("personId is not a valid person");
        }

        Rating rating = new Rating();
        rating.setDriverId(personId);
        rating.setScore(score);
        rating.setComment(comment);

        return rideRepository.save(rating);

    }
}
