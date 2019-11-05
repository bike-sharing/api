package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BicycleController {

    @Autowired
    private BicycleRepository bicycleRepository;

    @GetMapping("/bicycles")
    public List<Bicycle> getAll() {
        return bicycleRepository.getAll();
    }

    @GetMapping("/bicycles/available")
    public List<Bicycle> getAllAvailable() {
        return bicycleRepository.getAllByAvailabilityIsTrue();
    }

    @GetMapping("/bicycles/unavailable")
    public List<Bicycle> getAllUnavailable() {
        return bicycleRepository.getAllByAvailabilityIsFalse();
    }
}
