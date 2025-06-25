package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.Booking;
import com.csc3402.hotelhub1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // existing
    List<Booking> findByCustomer(Customer customer);

    // new:
    long countByStatus(String status);
    long countByCheckInDateAndStatus(LocalDate checkInDate, String status);
    List<Booking> findByStatusNot(String status);
    long countByStatusNot(String status);
}
