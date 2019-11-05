package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.repository.BicycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
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
    public boolean removeBicycle(@PathVariable String bicycleId) {
        return bicycleRepository.deleteByBicycleIdIs(UUID.fromString(bicycleId));
    }
}
