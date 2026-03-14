package org.example.factory.service;

import org.example.factory.entity.Travel;
import org.example.factory.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {

    private TravelRepository travelRepository;

    @Autowired
    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public Travel getTravel(Travel travel) {
        return travelRepository.save(travel);
    }

    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }

    public Travel updateTravel(Travel travel) {
        return travelRepository.save(travel);
    }

    public void deleteTravel(Travel travel) {
        travelRepository.delete(travel);
    }

    public Travel getTravelById(Long id){
        return travelRepository.getReferenceById(id);
    }
}
