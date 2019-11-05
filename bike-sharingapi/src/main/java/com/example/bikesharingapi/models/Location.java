package com.example.bikesharingapi.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "location")
public class Location {

    @Id
    private int locationId;
    private String name;
    private String longitude;
    private String latitude;
    private int radius;

    public Location(int locationId, String name, String longitude, String latitude, int radius) {
        this.locationId = locationId;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
