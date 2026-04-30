package org.example.factory.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class UserClient {
    private static final Logger logger = Logger.getLogger(UserClient.class.getName());

    private final RestTemplate restTemplate;
    private String rideServiceUrl;

    @Autowired
    public UserClient(RestTemplate restTemplate, @Value("${ride.service.url}") String rideServiceUrl) {
        this.restTemplate = restTemplate;
        this.rideServiceUrl = rideServiceUrl.endsWith("/") ? rideServiceUrl.substring(0, rideServiceUrl.length() - 1) : rideServiceUrl;
    }


    public void processDriverIds(List<Long> driverIds) {

        if (driverIds == null || driverIds.isEmpty()) {
            logger.info("No driver ids provided");
            return;
        }

        String url = this.rideServiceUrl + "/api/rides/process-driver-ids";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Long>> requestEntity = new HttpEntity<>(driverIds, headers);

        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    Void.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Successfully sent driver IDs to Ride Service. IDs: " + driverIds);
            } else {
                logger.warning("Received non-2xx status code from Ride Service: " + response.getStatusCode());
            }

        } catch (HttpClientErrorException e) {
            logger.log(Level.SEVERE, "HTTP Client Error sending driver IDs to Ride Service. Status: " + e.getStatusCode() + ", Body: " + e.getResponseBodyAsString(), e);
        } catch (ResourceAccessException e) {
            logger.log(Level.SEVERE, "Network error or Ride Service is unavailable. URL: " + url, e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred while sending driver IDs.", e);
        }
    }
}
