package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Location;
import com.example.bikesharingapi.models.PathCoordinates;
import com.example.bikesharingapi.models.PathDirections;
import com.example.bikesharingapi.repository.BicycleRepository;
import com.example.bikesharingapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PathController {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private BicycleRepository bicycleRepository;

    @GetMapping("/path-info")
    public PathDirections getById(@Valid @RequestBody PathCoordinates pathCoordinates) {
        PathDirections pathDirections = new PathDirections();
        Location closestLocation = getClosestLocation(
                Double.parseDouble(pathCoordinates.getCurrentLatitude()),
                Double.parseDouble(pathCoordinates.getCurrentLongitude()));

        pathDirections.setStartingPoint(closestLocation);

        pathDirections.setEndingPoint(getClosestLocation(
                Double.parseDouble(pathCoordinates.getDestinationLatitude()),
                Double.parseDouble(pathCoordinates.getDestinationLongitude())));

        pathDirections.setAvailableBicycles(bicycleRepository.getBicyclesByLocation_LocationId(closestLocation.getLocationId()));

        return pathDirections;
    }

    private Location getClosestLocation(double latitude, double longitude) {
        Location closestLocation = new Location();
        double closestLocationDistance = Double.MAX_VALUE;

        for(Location location : locationRepository.getAllBy()) {
            double distance = calculateDistance(
                    latitude,
                    Double.parseDouble(location.getLatitude()),
                    longitude,
                    Double.parseDouble(location.getLongitude()),
                    0.0, 0.0
            );
            if(distance < closestLocationDistance) {
                closestLocationDistance = distance;
                closestLocation.setRadius(location.getRadius());
                closestLocation.setLocationId(location.getLocationId());
                closestLocation.setLatitude(location.getLatitude());
                closestLocation.setLongitude(location.getLongitude());
                closestLocation.setName(location.getName());
            }
        }

        return closestLocation;
    }

    private static double calculateDistance(double latitude1, double latitude2, double longitude1,
                                            double longitude2, double altitude1, double altitude2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double lonDistance = Math.toRadians(longitude2 - longitude1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = altitude1 - altitude2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
