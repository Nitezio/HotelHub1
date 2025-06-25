package com.csc3402.hotelhub1.service;

import com.csc3402.hotelhub1.repository.BookingRepository;
import com.csc3402.hotelhub1.repository.CustomerRepository;
import com.csc3402.hotelhub1.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StaffService {

    private final CustomerRepository customerRepo;
    private final BookingRepository bookingRepo;

    @Autowired
    public StaffService(CustomerRepository customerRepo, BookingRepository bookingRepo) {
        this.customerRepo = customerRepo;
        this.bookingRepo = bookingRepo;
    }

    public long getTotalCustomers() {
        return customerRepo.count();
    }

    public long getTotalBookings() {
        return bookingRepo.count();
    }

    public long getNewBookings() {
        // “new” = just confirmed and not completed/cancelled
        return bookingRepo.countByStatus("CONFIRMED");
    }

    public long getPendingBookingsForToday() {
        LocalDate today = LocalDate.now();
        return bookingRepo.countByCheckInDateAndStatus(today, "CONFIRMED");
    }

    public long getCompletedBookings() {
        return bookingRepo.countByStatus("COMPLETED");
    }

    public long getCancelledBookings() {
        return bookingRepo.countByStatus("CANCELLED");
    }

    public double getTotalRevenue() {
        // sum totalPrice across all non-cancelled bookings
        List<Booking> bookings = bookingRepo.findByStatusNot("CANCELLED");
        return bookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();
    }
}
