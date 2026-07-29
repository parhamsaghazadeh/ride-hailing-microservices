package org.example.factory.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.factory.model.Enum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "payment-id" , nullable = false)
    private Long paymentId;
    @Column(name = "wallet-id" , nullable = false)
    private Long walletId;
    @Column(name = "amount" , nullable = false)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction-type",nullable = false)
    private Enum.transactionType transactionType;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction-status",nullable = false)
    private Enum.transactionStatus transactionStatus;
    @Column(name = "transaction-time" , nullable = false)
    private LocalDateTime transactionTime;
    @Column(name = "description",nullable = false)
    private String description;
}
