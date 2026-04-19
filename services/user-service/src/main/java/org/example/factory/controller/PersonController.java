package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.Model.Converter;
import org.example.factory.Model.PersonModel;
import org.example.factory.entity.Person;
import org.example.factory.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/person")
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private Converter converter;

    @GetMapping("/driverId")
    public ResponseEntity<Long> getDriverId() {
        Long id = personService.getDriverId();
        if ( id != null ) {
            return ResponseEntity.ok(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonModel>> retrievePerson() {
        try {
            List<Person> person = personService.getPersons();
            List<PersonModel> personModels = person.stream()
                    .map(converter::PersonConvert)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(personModels);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<PersonModel> searchPerson(@RequestParam long id) {
        try {
            Person person = personService.getPerson(id);
            PersonModel personModel = converter.PersonConvert(person);
            return ResponseEntity.ok(personModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonModel> createPerson(@RequestBody Person person) {
        try {
            Person person1 = personService.createPerson(person);
            PersonModel personModel = converter.PersonConvert(person1);
            return ResponseEntity.ok(personModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<PersonModel> updatePerson(@RequestBody Person person) {
        try {
            Person person1 = personService.updatePerson(person);
            PersonModel personModel = converter.PersonConvert(person1);
            return ResponseEntity.ok(personModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam long id) {
        try {
            personService.deletePerson(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
