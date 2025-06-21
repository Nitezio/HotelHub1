package com.csc3402.hotelhub1.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package aPackage;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate bookingDate;
    private String status;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Booking() {}

    public Booking(Long bookingId, Customer customer, Package aPackage, LocalDate checkInDate, LocalDate checkOutDate, LocalDate bookingDate, String status, List<Payment> payments, Room room) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.aPackage = aPackage;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingDate = bookingDate;
        this.status = status;
        this.payments = payments;
        this.room = room;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Derived property used in customer_dashboard.html
     */
    public double getTotalPrice() {
        if (checkInDate != null && checkOutDate != null && room != null && aPackage != null) {
            long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
            if (nights <= 0) return 0;
            return aPackage.getPackagePrice() + room.getPricePerNight() * nights;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", customer=" + customer +
                ", aPackage=" + aPackage +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                ", payments=" + payments +
                ", room=" + room +
                '}';
    }
}
