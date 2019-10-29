package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Location;
import com.example.bikesharingapi.repository.LocationRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
    private LocationRepository locationRepository;

    LocationController() {
        locationRepository = new LocationRepository();
    }

    @RequestMapping("/location/get/{id}")
    public Location get(
            @PathVariable("id") int id) {
        return locationRepository.get(id);
    }

}
