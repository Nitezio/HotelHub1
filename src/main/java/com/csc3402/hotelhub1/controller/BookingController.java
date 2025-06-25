package com.csc3402.hotelhub1.controller;

import com.csc3402.hotelhub1.model.Booking;
import com.csc3402.hotelhub1.model.Customer;
import com.csc3402.hotelhub1.service.BookingService;
import com.csc3402.hotelhub1.repository.PackageRepository;
import com.csc3402.hotelhub1.repository.RoomTypeRepository;
import com.csc3402.hotelhub1.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class BookingController {

    private final BookingService bookingService;
    private final PackageRepository packageRepo;
    private final RoomTypeRepository roomTypeRepo;
    private final CustomerRepository customerRepo;

    @Autowired
    public BookingController(BookingService bookingService,
                             PackageRepository packageRepo,
                             RoomTypeRepository roomTypeRepo,
                             CustomerRepository customerRepo) {
        this.bookingService = bookingService;
        this.packageRepo = packageRepo;
        this.roomTypeRepo = roomTypeRepo;
        this.customerRepo = customerRepo;
    }

    @GetMapping({"/home", "/dashboard"})
    public String dashboard(Model model,
                            @AuthenticationPrincipal UserDetails user) {
        String email = user.getUsername();
        List<Booking> bookings = bookingService.getBookingsByCustomerEmail(email);
        model.addAttribute("bookings", bookings);

        // unwrap Optional<Customer> before adding
        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + email));
        model.addAttribute("customer", customer);

        return "customer_dashboard";
    }

    @GetMapping("/booking")
    public String bookingForm(Model model) {
        model.addAttribute("packages", packageRepo.findAll());
        model.addAttribute("roomTypes", roomTypeRepo.findAll());
        return "booking_form";
    }

    @PostMapping("/booking/confirm")
    public String confirmBooking(@RequestParam Long packageId,
                                 @RequestParam Long roomTypeId,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
                                 @RequestParam String paymentMethod,
                                 @AuthenticationPrincipal UserDetails user) {
        bookingService.createBooking(
                packageId,
                roomTypeId,
                checkIn,
                checkOut,
                paymentMethod,
                user.getUsername()
        );
        return "redirect:/customer/dashboard";
    }

    @PostMapping("/booking/{id}/cancel")
    public String cancel(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/customer/dashboard";
    }

    @GetMapping("/booking/{id}/details")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("booking",
                bookingService.getBookingWithPaymentDetails(id));
        return "booking_details";
    }

    @GetMapping("/booking/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("booking",
                bookingService.getBookingWithPaymentDetails(id));
        model.addAttribute("packages", packageRepo.findAll());
        model.addAttribute("roomTypes", roomTypeRepo.findAll());
        return "booking_edit_form";
    }

    @PostMapping("/booking/{id}/edit")
    public String processEdit(@PathVariable Long id,
                              @RequestParam Long packageId,
                              @RequestParam Long roomTypeId,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
                              @AuthenticationPrincipal UserDetails user) {
        bookingService.updateBooking(
                id,
                packageId,
                roomTypeId,
                checkIn,
                checkOut,
                user.getUsername()
        );
        return "redirect:/customer/dashboard";
    }
}
