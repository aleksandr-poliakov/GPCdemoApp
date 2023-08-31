package com.example.gpcdemoapp.controllers;

import com.example.gpcdemoapp.dto.PaymentDTO;
import com.example.gpcdemoapp.dto.StatementDTO;
import com.example.gpcdemoapp.dto.TransactionDTO;
import com.example.gpcdemoapp.services.PaymentService;
import com.example.gpcdemoapp.services.StatementService;
import com.example.gpcdemoapp.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@RestController
@RequestMapping("/general")
public class GeneralController {

    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final StatementService statementService;
    private final ModelMapper modelMapper;


    @Autowired
    public GeneralController(PaymentService paymentService, TransactionService transactionService, StatementService statementService, ModelMapper modelMapper) {
        this.paymentService = paymentService;
        this.transactionService = transactionService;
        this.statementService = statementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/payments")
    @Operation(summary = "Get All Payments", description = "Get all payments data.")
    /**
     * Retrieves a list of payments.
     *
     * TODO queryPaginationDTO The pagination query parameters.
     * @return A Page object containing the list of Payment objects.
     */
    public List<PaymentDTO> getAllPayments() {
        // TODO make with page query to simplify this stream
        return paymentService.getAllPayments().stream().map((element) -> modelMapper.map(element, PaymentDTO.class)).toList();
    }

    @GetMapping("/transactions")
    @Operation(summary = "Get All Transactions", description = "Get all transactions data.")
    /**
     * Retrieves a list of transactions.
     *
     * TODO queryPaginationDTO The pagination query parameters.
     * @return A Page object containing the list of Transaction objects.
     */
    public List<TransactionDTO> getAllTransactions() {
        // TODO make with page query to simplify this stream
        return transactionService.getAllTransactions().stream().map((element) -> modelMapper.map(element, TransactionDTO.class)).toList();
    }

    @GetMapping("/statements")
    @Operation(summary = "Get All Statements", description = "Get all statements data.")
    /**
     * Retrieves a list of statements.
     *
     * TODO queryPaginationDTO The pagination query parameters.
     * @return A Page object containing the list of Statement objects.
     */
    public List<StatementDTO> getAllStatements() {
        // TODO make with page query to simplify this stream
        return statementService.getAllStatements().stream().map((element) -> modelMapper.map(element, StatementDTO.class)).toList();
    }
}
