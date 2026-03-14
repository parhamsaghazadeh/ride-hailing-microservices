package org.example.factory.repository;

import org.example.factory.Model.PersonLocationModel;
import org.example.factory.entity.PersonLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonLocationRepository extends JpaRepository<PersonLocation, Long> {

}
