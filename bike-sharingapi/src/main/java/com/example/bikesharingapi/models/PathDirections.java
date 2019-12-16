package com.example.bikesharingapi.models;

import java.util.List;

public class PathDirections {
    private Location startingPoint;
    private Location endingPoint;
    private List<Bicycle> availableBicycles;

    public PathDirections() {
    }

    public PathDirections(Location startingPoint, Location endingPoint, List<Bicycle> availableBicycles) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.availableBicycles = availableBicycles;
    }

    public Location getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Location startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Location getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(Location endingPoint) {
        this.endingPoint = endingPoint;
    }

    public List<Bicycle> getAvailableBicycles() {
        return availableBicycles;
    }

    public void setAvailableBicycles(List<Bicycle> availableBicycles) {
        this.availableBicycles = availableBicycles;
    }
}
