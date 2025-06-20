package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.Booking;
import com.csc3402.hotelhub1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(Customer customer);
}
