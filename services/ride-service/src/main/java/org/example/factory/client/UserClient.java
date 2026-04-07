package org.example.factory.client;

import org.example.factory.model.UserResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/users/email/{id}")
    UserResponseModel getUserByEmail(@PathVariable("id")  long id );
}
