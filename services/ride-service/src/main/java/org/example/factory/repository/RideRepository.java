package org.example.factory.repository;

import org.example.factory.entity.Rating;
import org.example.factory.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Rating, Long> {
}
