package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Location;
import com.example.bikesharingapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Location getById(@PathVariable String locationId) {
        return locationRepository.getByLocationId(UUID.fromString(locationId));
    }

    @DeleteMapping("/locations")
    public void removeLocation() {
        locationRepository.deleteAllBy();
    }

    @DeleteMapping("/location/{locationId}")
    public void removeLocation(@PathVariable String locationId) {
        locationRepository.deleteByLocationId(UUID.fromString(locationId));
    }

    @PutMapping("/location")
    public Location addLocation(@Valid @RequestBody Location location) {
        return locationRepository.saveAndFlush(location);
    }

    @PostMapping("/location")
    public Location updateLocation(@Valid @RequestBody Location location) {
        return locationRepository.saveAndFlush(location);
    }
}
