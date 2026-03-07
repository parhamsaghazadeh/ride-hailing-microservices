package org.example.factory.Model;

import org.example.factory.entity.Person;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class Converter {

    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public PersonModel PersonConvert(Person person) {
        PersonModel personModel = new PersonModel();
        personModel.setId(person.getId());
        personModel.setName(person.getName());
        personModel.setLastName(person.getLastname());
        personModel.setBirthDate(dateTimeFormatter.format(person.getBirthdate()));
        personModel.setNumber(person.getNumber());

        if (person.getAddress() != null) {
            AddressModel addressModel = new AddressModel();
            addressModel.setCity(person.getAddress().getCity());
            addressModel.setStreet(person.getAddress().getStreet());
            addressModel.setZipcode(person.getAddress().getZipcode());
            personModel.setAddress(addressModel);
        }
        return personModel;
    }
}
