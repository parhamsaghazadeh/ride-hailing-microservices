package org.example.factory.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PersonAddress {
    private String city;
    private String street;
    private String zipcode;
}
