package com.example.bikesharingapi.models;

public class PathCoordinates {
    private String currentLongitude;
    private String currentLatitude;
    private String destinationLongitude;
    private String destinationLatitude;

    public PathCoordinates(String currentLongitude, String currentLatitude, String destinationLongitude, String destinationLatitude) {
        this.currentLongitude = currentLongitude;
        this.currentLatitude = currentLatitude;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }
}
