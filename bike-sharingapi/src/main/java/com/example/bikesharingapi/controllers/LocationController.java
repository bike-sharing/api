package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;
}
