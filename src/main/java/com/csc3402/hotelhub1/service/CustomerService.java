package com.csc3402.hotelhub1.service;

import com.csc3402.hotelhub1.model.Customer;
import com.csc3402.hotelhub1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repo;
    private final PasswordEncoder encoder;

    @Autowired
    public CustomerService(CustomerRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public Customer save(Customer customer) {
        customer.setPassword(encoder.encode(customer.getPassword()));
        return repo.save(customer);
    }

    public Optional<Customer> findById(Long id) {
        return repo.findById(id);
    }

    public Customer findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found: " + email));
    }
}
