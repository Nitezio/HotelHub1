package com.csc3402.hotelhub1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    private String packageName;
    private Boolean foodIncluded;
    private Integer numberOfDays;
    private Integer maxPeople;
    private String facilitiesIncluded;
    private Double packagePrice;

    @OneToMany(mappedBy = "aPackage")
    private List<Booking> bookings;

    // getters and setters

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Boolean getFoodIncluded() {
        return foodIncluded;
    }

    public void setFoodIncluded(Boolean foodIncluded) {
        this.foodIncluded = foodIncluded;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getFacilitiesIncluded() {
        return facilitiesIncluded;
    }

    public void setFacilitiesIncluded(String facilitiesIncluded) {
        this.facilitiesIncluded = facilitiesIncluded;
    }

    public Double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(Double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Package() {
    }

    public Package(Long packageId, String packageName, Boolean foodIncluded, Integer numberOfDays, Integer maxPeople, String facilitiesIncluded, Double packagePrice, List<Booking> bookings) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.foodIncluded = foodIncluded;
        this.numberOfDays = numberOfDays;
        this.maxPeople = maxPeople;
        this.facilitiesIncluded = facilitiesIncluded;
        this.packagePrice = packagePrice;
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Package{" +
                "packageId=" + packageId +
                ", packageName='" + packageName + '\'' +
                ", foodIncluded=" + foodIncluded +
                ", numberOfDays=" + numberOfDays +
                ", maxPeople=" + maxPeople +
                ", facilitiesIncluded='" + facilitiesIncluded + '\'' +
                ", packagePrice=" + packagePrice +
                ", bookings=" + bookings +
                '}';
    }
}