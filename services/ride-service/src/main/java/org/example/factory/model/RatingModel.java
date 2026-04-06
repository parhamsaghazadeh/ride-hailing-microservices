package org.example.factory.model;

import lombok.Data;

@Data
public class RatingModel {
    private long id;
    private long driverId;
    private long userId;
    private long score;
    private String comment;
    private String timeStamp;
    private String ratingLevel;
}
