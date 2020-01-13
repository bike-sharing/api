package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.filters.AuthenticationFilter;
import com.example.bikesharingapi.models.Coordinates;
import com.example.bikesharingapi.models.Location;
import com.example.bikesharingapi.models.PathCoordinates;
import com.example.bikesharingapi.models.PathDirections;
import com.example.bikesharingapi.repository.BicycleRepository;
import com.example.bikesharingapi.repository.LocationRepository;
import com.example.bikesharingapi.algorithm.EucledianDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PathController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

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

        try {
            pathDirections.setEndingPoint(getClosestLocation(
                    Double.parseDouble(pathCoordinates.getDestinationLatitude()),
                    Double.parseDouble(pathCoordinates.getDestinationLongitude())));
        } catch (NullPointerException exception) {
            LOG.info("The ending point is not set. The user can freely use the bicycle.");
            pathDirections.setEndingPoint(null);
        }

        pathDirections.setAvailableBicycles(bicycleRepository.getBicyclesByLocation_LocationId(closestLocation.getLocationId()));

        return pathDirections;
    }

    private Location getClosestLocation(double latitude, double longitude) {
        EucledianDistance eucledianDistance = new EucledianDistance();
        Location closestLocation = new Location();
        double closestLocationDistance = Double.MAX_VALUE;
        Coordinates currentCoordinates = new Coordinates(longitude, latitude);

        for(Location location : locationRepository.getAllBy()) {
            Coordinates locationCoordinates = new Coordinates(
                    Double.parseDouble(location.getLongitude()),
                    Double.parseDouble(location.getLatitude()));

            double distance = eucledianDistance.calculate(currentCoordinates, locationCoordinates);
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
}
