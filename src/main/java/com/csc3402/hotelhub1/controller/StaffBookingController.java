package com.csc3402.hotelhub1.controller;

import com.csc3402.hotelhub1.model.Booking;
import com.csc3402.hotelhub1.model.Room;
import com.csc3402.hotelhub1.repository.BookingRepository;
import com.csc3402.hotelhub1.repository.PackageRepository;
import com.csc3402.hotelhub1.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/staff/bookings")
public class StaffBookingController {

    private final BookingRepository bookingRepo;
    private final PackageRepository packageRepo;
    private final RoomRepository roomRepo;

    @Autowired
    public StaffBookingController(BookingRepository bookingRepo,
                                  PackageRepository packageRepo,
                                  RoomRepository roomRepo) {
        this.bookingRepo = bookingRepo;
        this.packageRepo = packageRepo;
        this.roomRepo = roomRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("bookings", bookingRepo.findAll());
        return "bookings";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking Id: " + id));
        model.addAttribute("booking", booking);
        return "booking_view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking Id: " + id));
        model.addAttribute("booking", booking);
        model.addAttribute("packages", packageRepo.findAll());
        model.addAttribute("rooms", roomRepo.findAll()); // Send full list of rooms
        return "booking_edit_form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @RequestParam Long packageId,
                         @RequestParam Long roomId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                         @RequestParam String status,
                         Model model) {

        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking Id: " + id));

        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setStatus(status);
        booking.setAPackage(packageRepo.findById(packageId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package ID")));

        Room selectedRoom = roomRepo.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));
        booking.setRoom(selectedRoom);

        bookingRepo.save(booking);
        return "redirect:/staff/bookings";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        bookingRepo.deleteById(id);
        return "redirect:/staff/bookings";
    }
}
