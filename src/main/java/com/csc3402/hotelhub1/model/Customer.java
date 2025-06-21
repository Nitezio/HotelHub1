package com.csc3402.hotelhub1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private String role = "CUSTOMER";

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

    // getters and setters

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Customer() {
    }

    public Customer(Long customerId, String customerName, String email, String phoneNumber, String address, String password, String role, List<Booking> bookings) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.role = role;
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}