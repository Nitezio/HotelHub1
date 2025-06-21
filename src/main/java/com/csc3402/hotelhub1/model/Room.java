package com.csc3402.hotelhub1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomNumber;
    private String status;
    private Double pricePerNight;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    // getters and setters

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Room() {
    }

    public Room(Long roomId, String roomNumber, String status, Double pricePerNight, RoomType roomType, List<Booking> bookings) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.status = status;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber='" + roomNumber + '\'' +
                ", status='" + status + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", roomType=" + roomType +
                ", bookings=" + bookings +
                '}';
    }
}