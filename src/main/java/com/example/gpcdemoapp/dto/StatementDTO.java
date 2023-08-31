package com.example.gpcdemoapp.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author aleksandr on 31.08.2023
 * @projectName GPCdemoApp
 */
@Data
public class StatementDTO {
    private String type;
    private String accountNumber;
    private String truncatedAccountName;
    private Date oldBalanceDate;
    private Double oldBalance;
    private char oldBalanceSign;
    private Double newBalance;
    private char newBalanceSign;
    private Double debitTurnovers;
    private char debitTurnoversSign;
    private Double creditTurnovers;
    private char creditTurnoversSign;
    private String serialNumber;
    private Date accountingDate;
}
