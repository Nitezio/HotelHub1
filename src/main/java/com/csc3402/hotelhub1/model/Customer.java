package com.csc3402.hotelhub1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    /** maps to customer_name in data.sql */
    @NotBlank
    @Column(name = "customer_name")
    private String name;

    @Email @NotBlank
    @Column(unique = true)
    private String email;

    /** maps to phone_number */
    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotBlank
    private String password;

    /** maps to role */
    @NotBlank
    private String role = "ROLE_CUSTOMER";  // your data file already uses ROLE_STAFF etc.

    public Customer() {}

    // --- Getters & setters for every field ---

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
