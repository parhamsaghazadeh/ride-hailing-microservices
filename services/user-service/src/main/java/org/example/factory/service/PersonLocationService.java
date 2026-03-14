package org.example.factory.service;

import org.example.factory.entity.PersonLocation;
import org.example.factory.repository.PersonLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonLocationService {
    private PersonLocationRepository personLocationRepository;

    @Autowired
    public PersonLocationService(PersonLocationRepository personLocationRepository) {
        this.personLocationRepository = personLocationRepository;
    }

    public PersonLocation saveLocation(PersonLocation personLocation) {
        return personLocationRepository.save(personLocation);
    }

    public PersonLocation updateLocation(PersonLocation personLocation) {
        return personLocationRepository.save(personLocation);
    }

    public List<PersonLocation> findAllLocations() {
        return personLocationRepository.findAll();
    }

    public void deleteLocation(long id) {
        personLocationRepository.deleteById(id);
    }

    public PersonLocation getById(Long id) {
        return personLocationRepository.findById(id).orElse(null);
    }
}
