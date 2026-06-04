package org.example.factory.service;

import org.example.factory.entity.Person;
import org.example.factory.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private PersonRepository personRepository;

    public UserService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

public boolean isDriver(Long id) {

    Person person = personRepository
            .findById(id)
            .orElse(null);

    if (person == null) {
        return false;
    }

    return person.getRole()
            .getTitle()
            .equalsIgnoreCase("Driver");
}
}
