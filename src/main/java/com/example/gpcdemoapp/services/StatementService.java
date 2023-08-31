package com.example.gpcdemoapp.services;

import com.example.gpcdemoapp.dto.StatementDTO;
import com.example.gpcdemoapp.entity.Statement;
import com.example.gpcdemoapp.repositories.StatementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aleksandr on 31.08.2023
 * @projectName GPCdemoApp
 */
@Service
public class StatementService {
    private final StatementRepository statementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StatementService(StatementRepository statementRepository, ModelMapper modelMapper) {
        this.statementRepository = statementRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Saves and flushes the given statement data.
     *
     * @param statementDTO the DTO containing the statement data to be saved
     */
    public Statement saveAndFlush(StatementDTO statementDTO) {
        var statement = modelMapper.map(statementDTO, Statement.class);
        return statementRepository.saveAndFlush(statement);
    }

    /**
     * Retrieves a statements.
     *
     * @return statements.
     */
    public List<Statement> getAllStatements() {
        return statementRepository.findAll();
    }
}
