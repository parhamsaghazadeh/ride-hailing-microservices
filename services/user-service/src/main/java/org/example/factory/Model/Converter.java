package org.example.factory.Model;

import org.example.factory.entity.Person;
import org.example.factory.entity.PersonLocation;
import org.example.factory.entity.PersonWallet;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

@Component
public class Converter {

    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");

    public PersonModel PersonConvert(Person person) {
        PersonModel personModel = new PersonModel();
        personModel.setId(person.getId());
        personModel.setName(person.getName());
        personModel.setLastName(person.getLastname());
        personModel.setGenderId(person.getGenderId().getId());
        personModel.setBirthDate(dateTimeFormatter.format(person.getBirthdate()));
        personModel.setNumber(person.getNumber());

        if (person.getPersonAddress() != null) {
            AddressModel addressModel = new AddressModel();
            addressModel.setCity(person.getPersonAddress().getCity());
            addressModel.setStreet(person.getPersonAddress().getStreet());
            addressModel.setZipcode(person.getPersonAddress().getZipcode());
            personModel.setAddress(addressModel);
        }
        return personModel;
    }

    public PersonWalletModel toPersonWalletModel(PersonWallet personWallet) {
        PersonWalletModel personWalletModel = new PersonWalletModel();
        personWalletModel.setPersonId(personWallet.getPersonId().getId());
        personWalletModel.setId(personWallet.getId());
        personWalletModel.setBalance(DECIMAL_FORMAT.format(personWallet.getBalance()));
        personWalletModel.setCreated_at(dateTimeFormatter.format(personWallet.getCreatedAt()));
        personWalletModel.setCurrency(personWallet.getCurrency());
        personWalletModel.setUpdated_at(dateTimeFormatter.format(personWallet.getUpdateAt()));
        return personWalletModel;
    }

    public PersonLocationModel toPersonLocationModel(PersonLocation personLocation) {
        PersonLocationModel personLocationModel = new PersonLocationModel();
        personLocationModel.setId(personLocation.getId());
        personLocationModel.setNameLocation(personLocation.getLocationName());
        personLocationModel.setCreatedAt(dateTimeFormatter.format(personLocation.getCreatedAt()));
        personLocationModel.setIsDefault(personLocationModel.getIsDefault());
        return personLocationModel;
    }
}
