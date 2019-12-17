package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.repository.BicycleRepository;
import com.example.bikesharingapi.repository.LocationRepository;
import com.example.bikesharingapi.utils.QRcodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    @RequestMapping(produces = MediaType.IMAGE_JPEG_VALUE, value = "/bicycle/{bicycleId}/qr", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getQRById(@PathVariable String bicycleId) {
        return QRcodeGenerator.GetQRcodeBytestream(bicycleId);
    }

    @DeleteMapping("/bicycles")
    @Transactional
    public void removeBicycle() {
        bicycleRepository.deleteAllBy();
    }

    @DeleteMapping("/bicycle/{bicycleId}")
    @Transactional
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
