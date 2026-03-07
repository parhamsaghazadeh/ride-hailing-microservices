package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "gender")
@Data
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title" , length = 50 ,nullable = false)
    private String title;
}
