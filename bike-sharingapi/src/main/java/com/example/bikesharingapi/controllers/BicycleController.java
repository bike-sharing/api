package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.repository.BicycleRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BicycleController {
    private BicycleRepository bicycleRepository;

    public BicycleController(){
        bicycleRepository = new BicycleRepository();
    }

    @RequestMapping("/bicycle/get")
    public ArrayList<Bicycle> getAll() {
        return bicycleRepository.getAll();
    }

}
