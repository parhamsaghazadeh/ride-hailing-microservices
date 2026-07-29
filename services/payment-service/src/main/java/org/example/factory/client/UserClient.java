package org.example.factory.client;

import org.example.factory.model.PersonWalletModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {
    private RestTemplate restTemplate;

    public PersonWalletModel getPersonWalletById(Long personId){
        return restTemplate.getForObject(
                "http://localhost:8080/user/Wallet/api" + personId, PersonWalletModel.class);
    }
}
