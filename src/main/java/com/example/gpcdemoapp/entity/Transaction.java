package com.example.gpcdemoapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * @author aleksandr on 31.08.2023
 * @projectName GPCdemoApp
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "transaction_tbl")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "type")
    private String type;
    @Column(name = "assigned_account_number")
    private String assignedAccountNumber;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "accounting_code")
    private char accountingCode;
    @Column(name = "variable_symbol")
    private String variableSymbol;
    @Column(name = "constant_symbol")
    private String constantSymbol;
    @Column(name = "specificSymbol")
    private String specificSymbol;
    @Column(name = "currency")
    private String currency;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "zero")
    private char zero;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "due_date")
    private Date dueDate;
}
