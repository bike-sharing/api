package com.example.bikesharingapi.algorithm;

import com.example.bikesharingapi.models.Coordinates;

public class EucledianDistance implements Distance {
    @Override
    public double calculate(Coordinates a, Coordinates b) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(a.getLatitude() - b.getLatitude());
        double lonDistance = Math.toRadians(a.getLongitude() - b.getLongitude());
        double x = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(a.getLatitude())) * Math.cos(Math.toRadians(b.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));
        double distance = R * c * 1000; // convert to meters


        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
