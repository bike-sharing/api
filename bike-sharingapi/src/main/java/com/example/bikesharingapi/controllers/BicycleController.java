package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.repository.BicycleRepository;
import com.example.bikesharingapi.utils.QRcodeGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@SuppressWarnings("PointlessBooleanExpression")
@RestController
public class BicycleController {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum ChangeBicycleStatus {
        BORROW_SUCCESS("Bicycle was borrowed with success", 1),
        BORROW_FAIL("Bicycle can't be borrowed", -1),
        RETURN_SUCCESS("Bicycle was returned with success", 1);

        private final String message;
        private final int statusCode;

        ChangeBicycleStatus(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }

    @Autowired
    private BicycleRepository bicycleRepository;

    @GetMapping("/bicycles")
    public List<Bicycle> getAll() {
        return bicycleRepository.getAllBy();
    }

    @GetMapping(value = "/bicycle/{bicycleId}/borrow", produces = MediaType.APPLICATION_JSON_VALUE)
    public String borrowBicycle(@PathVariable String bicycleId) {
        Bicycle bicycle = bicycleRepository.getByBicycleId(UUID.fromString(bicycleId));
        try {
            if (bicycle.getAvailability() == true) {
                bicycle.setAvailability(false);
                bicycleRepository.saveAndFlush(bicycle);
                return new ObjectMapper().writeValueAsString(ChangeBicycleStatus.BORROW_SUCCESS);
            } else {
                return new ObjectMapper().writeValueAsString(ChangeBicycleStatus.BORROW_FAIL);
            }
        } catch (JsonProcessingException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/bicycle/{bicycleId}/return", produces = MediaType.APPLICATION_JSON_VALUE)
    public String returnBicycle(@PathVariable String bicycleId) {
        Bicycle bicycle = bicycleRepository.getByBicycleId(UUID.fromString(bicycleId));
        bicycle.setAvailability(true);
        bicycleRepository.saveAndFlush(bicycle);
        try {
            return new ObjectMapper().writeValueAsString(ChangeBicycleStatus.RETURN_SUCCESS);
        } catch (JsonProcessingException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @GetMapping("/bicycle/{bicycleId}")
    public Bicycle getById(@PathVariable String bicycleId) {
        return bicycleRepository.getByBicycleId(UUID.fromString(bicycleId));
    }

    @RequestMapping(produces = MediaType.IMAGE_JPEG_VALUE, value = "/bicycle/{bicycleId}/qr", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getQRById(@PathVariable String bicycleId, final HttpServletRequest request) {
        return QRcodeGenerator.GetQRcodeBytestream(bicycleId, request.getServerName());
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
        public Bicycle addBicycle(@Valid @RequestBody Bicycle bicycle) throws Exception {

        if(bicycle.getCurrentLatitude() != null && bicycle.getCurrentLongitude() == null
            || bicycle.getCurrentLatitude() == null && bicycle.getCurrentLongitude() != null)
            throw new Exception("Invalid coordinates.");

        if(bicycle.getCurrentLongitude() != null && bicycle.getCurrentLatitude() != null) {
            double latitude = Double.parseDouble(bicycle.getCurrentLatitude());
            double longitude = Double.parseDouble(bicycle.getCurrentLongitude());

            if(latitude < -90 || latitude > 90
                    || longitude < -180 || longitude > 180)
                throw new Exception("Invalid coordinates.");
        }
        return bicycleRepository.saveAndFlush(bicycle);
    }

    @PostMapping("/bicycle")
    public Bicycle updateBicycle(@Valid @RequestBody Bicycle bicycle) {
        return bicycleRepository.saveAndFlush(bicycle);
    }
}
