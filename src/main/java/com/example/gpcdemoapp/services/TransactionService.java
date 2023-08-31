package com.example.gpcdemoapp.services;

import com.example.gpcdemoapp.dto.TransactionDTO;
import com.example.gpcdemoapp.entity.Transaction;
import com.example.gpcdemoapp.repositories.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aleksandr on 31.08.2023
 * @projectName GPCdemoApp
 */
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Saves and flushes the given transaction data.
     *
     * @param transactionDTO the DTO containing the transaction data to be saved
     */
    public Transaction saveAndFlush(TransactionDTO transactionDTO) {
        var transaction = modelMapper.map(transactionDTO, Transaction.class);
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be a positive value.");
        }
        return transactionRepository.saveAndFlush(transaction);
    }

    /**
     * Retrieves a transactions.
     *
     * @return transactions.
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
