package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.factory.model.Enum;

import java.time.LocalDateTime;



@Entity
@Table(name = "rating")
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "driver_id",nullable = false)
    private Long driverId;
    //امتیاز 1-10
    @Column(name = "score",nullable = false)
    private long score;
    @Column(name = "comment" , nullable = false ,length = 250)
    private String comment;
    @Column(name = "time_stamp" , nullable = false)
    private LocalDateTime timeStamp;
    @Enumerated(EnumType.STRING)
    @Column(name = "rating_level" , nullable = false)
    private Enum.RatingLevel ratingLevel;

}