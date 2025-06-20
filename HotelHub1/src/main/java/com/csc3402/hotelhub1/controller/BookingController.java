// src/main/java/com/csc3402/hotelhub1/controller/BookingController.java
package com.csc3402.hotelhub1.controller;

import com.csc3402.hotelhub1.model.Booking;
import com.csc3402.hotelhub1.repository.PackageRepository;
import com.csc3402.hotelhub1.repository.RoomTypeRepository;
import com.csc3402.hotelhub1.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class BookingController {

    private final BookingService bookingService;
    private final PackageRepository packageRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public BookingController(BookingService bookingService,
                             PackageRepository packageRepository,
                             RoomTypeRepository roomTypeRepository) {
        this.bookingService = bookingService;
        this.packageRepository = packageRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    // 1) Show booking list
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        List<Booking> bookings = bookingService.getBookingsByCustomerEmail(principal.getName());
        model.addAttribute("bookings", bookings);
        return "customer_dashboard";      // → templates/customer_dashboard.html
    }

    // 2) New‑booking form
    @GetMapping("/booking")
    public String bookingForm(Model model) {
        model.addAttribute("packages", packageRepository.findAll());
        model.addAttribute("roomTypes", roomTypeRepository.findAll());
        return "booking_form";           // → templates/booking_form.html
    }

    // 3) Process booking + payment
    @PostMapping("/booking/confirm")
    public String confirmBooking(@RequestParam Long packageId,
                                 @RequestParam Long roomTypeId,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
                                 @RequestParam String paymentMethod,
                                 Principal principal) {
        bookingService.createBooking(
                packageId, roomTypeId, checkIn, checkOut,
                paymentMethod, principal.getName()
        );
        return "redirect:/customer/dashboard";
    }

    // 4) Cancel a booking
    @PostMapping("/booking/{id}/cancel")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/customer/dashboard";
    }

    // 5) View details (including payment + print)
    @GetMapping("/booking/{id}/details")
    public String viewBooking(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingWithPaymentDetails(id);
        model.addAttribute("booking", booking);
        return "booking_details";        // → templates/booking_details.html
    }
}
