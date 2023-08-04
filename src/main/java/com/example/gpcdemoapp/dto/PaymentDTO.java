package com.example.gpcdemoapp.dto;

import lombok.Data;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@Data
public class PaymentDTO {
    private String senderAccount;
    private String recipientAccount;
    private Float amount;
}
