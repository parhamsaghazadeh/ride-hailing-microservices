package org.example.factory.client;

import org.example.factory.model.UserResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "user-service" , url = "${services.user.url}")
public interface UserClient {
    @GetMapping("/api/users/person/{personId}/role")
    String getUserRole(@PathVariable("personId") Long driverId);
}
