package com.example.bikesharingapi.models;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "bicycle")
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bicycleId;
    private Date lastRevisionTime;
    private Boolean availability;
    @ManyToOne
    private State state;
    @ManyToOne
    private Location location;
    private String currentLatitude;
    private String currentLongitude;
    private UUID userId;
    private String QRcode;

    public Bicycle() {

    }

    public Bicycle(Date lastRevisionTime, Boolean availability, State state, Location location, String currentLatitude, String currentLongitude, UUID userId, String QRcode) {
        this.lastRevisionTime = lastRevisionTime;
        this.availability = availability;
        this.state = state;
        this.location = location;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.userId = userId;
        this.QRcode = QRcode;
    }

    public UUID getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(UUID bicycleId) {
        this.bicycleId = bicycleId;
    }

    public Date getLastRevisionTime() {
        return lastRevisionTime;
    }

    public void setLastRevisionTime(Date lastRevisionTime) {
        this.lastRevisionTime = lastRevisionTime;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }
}
