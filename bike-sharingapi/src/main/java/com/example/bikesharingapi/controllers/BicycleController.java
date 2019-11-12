package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.repository.BicycleRepository;
import com.example.bikesharingapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
public class BicycleController {

    @Autowired
    private BicycleRepository bicycleRepository;

    @GetMapping("/bicycles")
    public List<Bicycle> getAll() {
        return bicycleRepository.getAllBy();
    }

    @GetMapping("/bicycle/{bicycleId}")
    public Bicycle getById(@PathVariable String bicycleId) {
        return bicycleRepository.getByBicycleId(UUID.fromString(bicycleId));
    }

    @DeleteMapping("/bicycles")
    public void removeBicycle() {
        bicycleRepository.deleteAllBy();
    }

    @DeleteMapping("/bicycle/{bicycleId}")
    public void removeBicycle(@PathVariable String bicycleId) {
        bicycleRepository.deleteByBicycleIdIs(UUID.fromString(bicycleId));
    }

    @PutMapping("/bicycle")
    public Bicycle addBicycle(@Valid @RequestBody Bicycle bicycle) {
        return bicycleRepository.saveAndFlush(bicycle);
    }

    @PostMapping("/bicycle")
    public Bicycle updateBicycle(@Valid @RequestBody Bicycle bicycle) {
        return bicycleRepository.saveAndFlush(bicycle);
    }
}
