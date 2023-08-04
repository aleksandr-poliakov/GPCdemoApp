package com.example.gpcdemoapp.repositories;

import com.example.gpcdemoapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Payment p WHERE p.senderAccount = ?1 AND p.recipientAccount = ?2")
    boolean existByLocalAccount(String senderAccount, String recipientAccount);
}
