package com.example.gpcdemoapp.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author aleksandr on 31.08.2023
 * @projectName GPCdemoApp
 */
@Data
public class TransactionDTO {
    private String type;
    private String assignedAccountNumber;
    private String accountNumber;
    private String documentNumber;
    private Double amount;
    private char accountingCode;
    private String variableSymbol;
    private String constantSymbol;
    private String specificSymbol;
    private String currency;
    private String clientName;
    private char zero;
    private String currencyCode;
    private Date dueDate;
}
