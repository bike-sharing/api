package com.example.bikesharingapi.algorithm;

import com.example.bikesharingapi.models.Coordinates;

public class Centroid {
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != getClass())
            return false;
        else
            return this.coordinates.getLatitude() == ((Coordinates) obj).getLatitude()
                    && this.coordinates.getLongitude() == ((Coordinates) obj).getLongitude();
    }
}
