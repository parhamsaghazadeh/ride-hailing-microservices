package org.example.factory.service;

import org.example.factory.entity.Person;
import org.example.factory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }

    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }
}
