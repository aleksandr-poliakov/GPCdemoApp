package com.example.gpcdemoapp.services;

import com.example.gpcdemoapp.dto.PaymentDTO;
import com.example.gpcdemoapp.entity.Payment;
import com.example.gpcdemoapp.repositories.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Saves and flushes the given payment data.
     *
     * @param paymentDTO the DTO containing the payment data to be saved
     */
    public Payment saveAndFlush(PaymentDTO paymentDTO) {
        var payment = modelMapper.map(paymentDTO, Payment.class);
        if (payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be a positive value.");
        }
        return paymentRepository.saveAndFlush(payment);
    }

    /**
     * Checks if a payment exists with the given sender and recipient account.
     *
     * @param senderAccount The sender account of the Payment.
     * @param recipientAccount The recipient account of the payment.
     * @return {@code true} if a payment exists with the given accounts;
     *         {@code false} otherwise.
     */
    public boolean existByAccount(String senderAccount, String recipientAccount) {
        return paymentRepository.existByLocalAccount(senderAccount, recipientAccount);
    }

    /**
     * Retrieves a payments.
     *
     * @return payments.
     */
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
