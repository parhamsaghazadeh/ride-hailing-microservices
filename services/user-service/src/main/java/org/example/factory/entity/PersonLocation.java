package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "person_location")
@Data
public class PersonLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "person_id" , foreignKey = @ForeignKey(name = "FK_person_location"))
    private Person person;
    @Column(name = "location_name" , length = 50 , nullable = false)
    private String locationName;
    @Column(name = "is_default" , nullable = false)
    private boolean isDefault = false;
    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;
}
