package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "person_wallet")
@Data
public class PersonWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "FK-person-wallet"))
    private Person personId;
    //موجودی کیف پول
    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;
    //واحد پول
    @Column(name = "currency", nullable = false, length = 50)
    private String currency;
    //ایجاد زمان پول
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    //آخرین بروزرسانی کیف پول
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;
}
