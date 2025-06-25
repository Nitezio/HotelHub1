package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// CustomerRepository.java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}


