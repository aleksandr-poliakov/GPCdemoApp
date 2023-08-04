package com.example.gpcdemoapp.controllers;

import com.example.gpcdemoapp.dto.PaymentDTO;
import com.example.gpcdemoapp.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentController(PaymentService paymentService,
                             ModelMapper modelMapper) {
        this.paymentService = paymentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @Operation(summary = "Get All Payments", description = "Get all payments data.")
    /**
     * Retrieves a list of payments.
     *
     * TODO queryPaginationDTO The pagination query parameters.
     * @return A Page object containing the list of Payment objects.
     */
    public List<PaymentDTO> getAllPayments() {
        // TODO make with page query to simplify this stream
        return paymentService.getAllPayments().stream().map((element) -> modelMapper.map(element, PaymentDTO.class)).collect(Collectors.toList());
    }
}
