package org.example.factory.repository;

import org.example.factory.entity.Person;
import org.example.factory.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByRole(Role role);

}
