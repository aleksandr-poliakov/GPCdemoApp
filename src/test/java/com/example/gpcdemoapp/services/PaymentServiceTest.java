package com.example.gpcdemoapp.services;

import com.example.gpcdemoapp.dto.PaymentDTO;
import com.example.gpcdemoapp.entity.Payment;
import com.example.gpcdemoapp.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    PaymentService paymentService;

    @BeforeEach
    void prepare() {
        paymentService = new PaymentService(paymentRepository, modelMapper);
    }

    @Test
    void testSaveAndFlush() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setSenderAccount("Sender");
        paymentDTO.setRecipientAccount("Recipient");
        paymentDTO.setAmount(100.00f); // Using Float for amount

        Payment payment = new Payment();
        payment.setSenderAccount("Sender");
        payment.setRecipientAccount("Recipient");
        payment.setAmount(100.00f);

        when(modelMapper.map(paymentDTO, Payment.class)).thenReturn(payment);
        when(paymentRepository.saveAndFlush(payment)).thenReturn(payment);

        var result = paymentService.saveAndFlush(paymentDTO);


        verify(modelMapper).map(paymentDTO, Payment.class);
        verify(paymentRepository).saveAndFlush(payment);
        assertEquals(payment, result);
    }


    @Test
    void testExistByLocalName_Exists() {
        when(paymentRepository.existByLocalAccount("Sender", "Recipient")).thenReturn(true);

        boolean result = paymentService.existByAccount("Sender", "Recipient");

        assertTrue(result);
    }

    @Test
    void testExistByLocalName_NotExists() {
        when(paymentRepository.existByLocalAccount("Sender", "Recipient")).thenReturn(false);

        boolean result = paymentService.existByAccount("Sender", "Recipient");

        assertFalse(result);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(UUID.randomUUID(),"Sender1", "Recipient1", 100.00F));
        paymentList.add(new Payment(UUID.randomUUID(),"Sender2", "Recipient2", 200.00F));

        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(paymentList.size(), result.size());
        assertEquals(paymentList.get(0).getSenderAccount(), result.get(0).getSenderAccount());
        assertEquals(paymentList.get(1).getRecipientAccount(), result.get(1).getRecipientAccount());
    }
}