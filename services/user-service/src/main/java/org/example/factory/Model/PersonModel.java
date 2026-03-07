package org.example.factory.Model;

import lombok.Data;

@Data
public class PersonModel {
    private Long id;
    private String name;
    private String lastName;
    private String birthDate;
    private long genderId;
    private long roleId;
    private AddressModel address;
    private String number;
}
