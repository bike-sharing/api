package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Location;
import com.example.bikesharingapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    public List<Location> getAll() {
        return locationRepository.getAllBy();
    }

    @GetMapping("/location/{locationId}")
    public List<Location> getById(@PathVariable String locationId) {
        return locationRepository.getByLocationId(UUID.fromString(locationId));
    }

    @DeleteMapping("/locations")
    public void removeBicycle() {
        locationRepository.deleteAllBy();
    }

    @DeleteMapping("/location/{locationId}")
    public void removeBicycle(@PathVariable String locationId) {
        locationRepository.deleteByLocationId(UUID.fromString(locationId));
    }
}
