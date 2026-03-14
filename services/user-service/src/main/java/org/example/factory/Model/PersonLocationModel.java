package org.example.factory.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonLocationModel {
    private Long id;
    private String createdAt;
    private String nameLocation;
    private String isDefault;
}
