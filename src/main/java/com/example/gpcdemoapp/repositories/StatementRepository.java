package com.example.gpcdemoapp.repositories;

import com.example.gpcdemoapp.entity.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author aleksandr on 31.08.2023
 * @projectName GPCdemoApp
 */
@Repository
public interface StatementRepository extends JpaRepository<Statement, UUID> {
}
