package com.csc3402.hotelhub1.service;

import com.csc3402.hotelhub1.model.Customer;
import com.csc3402.hotelhub1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer c = customerRepository.findByEmail(email);
        if (c == null) {
            System.out.println("LOGIN FAIL: No user for email=" + email);
            throw new UsernameNotFoundException("No user with email: " + email);
        }

        System.out.println("LOGIN ATTEMPT: Found user=" + c.getCustomerName() +
                " role=" + c.getRole() +
                " hashedPassword=" + c.getPassword());

        String role = c.getRole().startsWith("ROLE_") ? c.getRole() : "ROLE_" + c.getRole();

        return User.withUsername(c.getEmail())
                .password(c.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(role)))
                .build();
    }
}
