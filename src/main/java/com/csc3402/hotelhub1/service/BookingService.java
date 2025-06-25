package com.csc3402.hotelhub1.service;

import com.csc3402.hotelhub1.model.Booking;
import com.csc3402.hotelhub1.model.Customer;
import com.csc3402.hotelhub1.model.Package;
import com.csc3402.hotelhub1.model.Payment;
import com.csc3402.hotelhub1.model.Room;
import com.csc3402.hotelhub1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final CustomerRepository customerRepo;
    private final PackageRepository packageRepo;
    private final RoomRepository roomRepo;
    private final RoomTypeRepository roomTypeRepo;
    private final PaymentRepository paymentRepo;

    @Autowired
    public BookingService(BookingRepository bookingRepo,
                          CustomerRepository customerRepo,
                          PackageRepository packageRepo,
                          RoomRepository roomRepo,
                          RoomTypeRepository roomTypeRepo,
                          PaymentRepository paymentRepo) {
        this.bookingRepo = bookingRepo;
        this.customerRepo = customerRepo;
        this.packageRepo = packageRepo;
        this.roomRepo = roomRepo;
        this.roomTypeRepo = roomTypeRepo;
        this.paymentRepo = paymentRepo;
    }

    public List<Booking> getBookingsByCustomerEmail(String email) {
        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found: " + email));
        return bookingRepo.findByCustomer(customer);
    }

    public Booking getBookingWithPaymentDetails(Long bookingId) {
        return bookingRepo.findById(bookingId).orElse(null);
    }

    public void cancelBooking(Long bookingId) {
        bookingRepo.findById(bookingId).ifPresent(b -> {
            b.setStatus("CANCELLED");
            Room room = b.getRoom();
            room.setStatus("AVAILABLE");
            roomRepo.save(room);
            bookingRepo.save(b);
        });
    }

    public void createBooking(Long packageId,
                              Long roomTypeId,
                              LocalDate checkIn,
                              LocalDate checkOut,
                              String paymentMethod,
                              String customerEmail) {
        Customer customer = customerRepo.findByEmail(customerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found: " + customerEmail));
        Package pack = packageRepo.findById(packageId).orElseThrow();
        Room room = roomRepo.findFirstByRoomType_RoomTypeIdAndStatus(roomTypeId, "AVAILABLE");
        if (room == null) {
            return;
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setBookingDate(LocalDate.now());
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setStatus("CONFIRMED");
        booking.setRoom(room);
        booking.setAPackage(pack); // ✅ corrected

        bookingRepo.save(booking);

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus("PAID");
        paymentRepo.save(payment);

        room.setStatus("BOOKED");
        roomRepo.save(room);
    }

    public void updateBooking(Long bookingId,
                              Long newPackageId,
                              Long newRoomTypeId,
                              LocalDate newCheckIn,
                              LocalDate newCheckOut,
                              String customerEmail) {
        bookingRepo.findById(bookingId).ifPresent(b -> {
            if (!b.getCustomer().getEmail().equals(customerEmail)) {
                return;
            }

            Room oldRoom = b.getRoom();
            oldRoom.setStatus("AVAILABLE");
            roomRepo.save(oldRoom);

            Package pack = packageRepo.findById(newPackageId).orElseThrow();
            Room newRoom = roomRepo.findFirstByRoomType_RoomTypeIdAndStatus(newRoomTypeId, "AVAILABLE");
            if (newRoom == null) {
                return;
            }

            b.setAPackage(pack); // ✅ corrected
            b.setRoom(newRoom);
            b.setCheckInDate(newCheckIn);
            b.setCheckOutDate(newCheckOut);
            b.setStatus("CONFIRMED");
            bookingRepo.save(b);

            newRoom.setStatus("BOOKED");
            roomRepo.save(newRoom);
        });
    }
}
