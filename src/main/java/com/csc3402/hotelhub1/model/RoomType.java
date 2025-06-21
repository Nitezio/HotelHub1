package com.csc3402.hotelhub1.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Long roomTypeId;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

    // Constructors
    public RoomType() {}

    public RoomType(Long roomTypeId, String typeName, String description, BigDecimal pricePerNight, List<Room> rooms) {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.rooms = rooms;
    }

    // Getters and setters
    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "roomTypeId=" + roomTypeId +
                ", typeName='" + typeName + '\'' +
                ", description='" + description + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", rooms=" + rooms +
                '}';
    }
}
