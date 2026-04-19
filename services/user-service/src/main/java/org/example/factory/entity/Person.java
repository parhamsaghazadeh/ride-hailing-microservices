package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @ManyToOne
    @JoinColumn(name = "gender-id", foreignKey = @ForeignKey(name = "FK_person_gender"))
    private Gender genderId;
    @ManyToOne
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_person_role"))
    private Role role;
    @Column(name = "number", length = 50, nullable = false)
    private String number;
    @Embedded
    private PersonAddress personAddress;
}
