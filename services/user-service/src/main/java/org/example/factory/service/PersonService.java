package org.example.factory.service;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.entity.Person;
import org.example.factory.entity.Role;
import org.example.factory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class PersonService {
    private PersonRepository personRepository;

    private Map<String, Long> roleTitleToIdMap;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
        PersonService();
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


    public void PersonService(){
        roleTitleToIdMap=new HashMap<>();
        roleTitleToIdMap.put("Driver",1L);
        roleTitleToIdMap.put("Traveller",2L);
    }

    public List<Person> getPersonsByRole(String role) {
        Long roleId = roleTitleToIdMap.get(role);

        if (roleId == null) {
            return Collections.emptyList();
        }

        Role temoRole = new Role();
        temoRole.setId(roleId);
        return personRepository.findByRole(temoRole);
    }


}
