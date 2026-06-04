package org.example.factory.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {
    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isDriver(Long driverId) {
        String url =
                "http://localhost:8080/users/"
                        + driverId
                        + "/is-driver";
        return restTemplate.getForObject(
                url,
                Boolean.class
        );
    }
}
