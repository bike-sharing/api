package com.example.bikesharingapi.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "bicycle")
public class Bicycle {

    @Id
    private String bicycleId;
    private Date lastRevisionTime;
    private Boolean availability;
    @ManyToOne
    private State state;
    @ManyToOne
    private Location location;
    private String currentLatitude;
    private String currentLongitude;
    private String userId;

    public Bicycle(String bicycleId, Date lastRevisionTime, Boolean availability, State state, Location location, String currentLatitude, String currentLongitude, String userId) {
        this.bicycleId = bicycleId;
        this.lastRevisionTime = lastRevisionTime;
        this.availability = availability;
        this.state = state;
        this.location = location;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.userId = userId;
    }

    public String getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(String bicycleId) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
