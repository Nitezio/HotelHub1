package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}