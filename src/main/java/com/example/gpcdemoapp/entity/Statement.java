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
@Table(name = "statement_tbl")
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "type")
    private String type;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "truncated_account_name")
    private String truncatedAccountName;
    @Column(name = "old_balance_date")
    private Date oldBalanceDate;
    @Column(name = "old_balance")
    private Double oldBalance;
    @Column(name = "old_balance_sign")
    private char oldBalanceSign;
    @Column(name = "new_balance")
    private Double newBalance;
    @Column(name = "new_balance_sign")
    private char newBalanceSign;
    @Column(name = "debig_turnover")
    private Double debitTurnovers;
    @Column(name = "debit_turnover_sign")
    private char debitTurnoversSign;
    @Column(name = "credit_turnover")
    private Double creditTurnovers;
    @Column(name = "credit_turnover_sign")
    private char creditTurnoversSign;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "accounting_date")
    private Date accountingDate;
}
