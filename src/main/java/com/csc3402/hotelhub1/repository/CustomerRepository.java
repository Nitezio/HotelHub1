package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}