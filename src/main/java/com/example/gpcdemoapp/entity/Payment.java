package com.example.gpcdemoapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "payment_tbl")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "sender_account")
    private String senderAccount;
    @Column(name = "recipient_account")
    private String recipientAccount;
    @Column(name = "amount")
    private Float amount;
}
