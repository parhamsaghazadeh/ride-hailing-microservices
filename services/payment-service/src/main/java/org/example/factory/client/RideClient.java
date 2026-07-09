package org.example.factory.client;

import org.example.factory.model.RideModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RideClient {
    private final RestTemplate restTemplate;

    public RideClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RideModel getRideById(Long RideId){
        return restTemplate.getForObject(
                "http://localhost:8081/ride/api/ride/" + RideId, RideModel.class
        );
    }
}
