package org.example.factory.model;

import lombok.Data;

@Data
public class UserResponseModel {
    private Long id;
    private String name;
    private String lastName;
    private String birthDate;
    private long genderId;
    private long roleId;
    private String number;
}
