package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.models.State;
import com.example.bikesharingapi.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping("/states")
    public List<State> getAll() {
        return stateRepository.getAllBy();
    }

    @GetMapping("/state/{stateId}")
    public State getById(@PathVariable String stateId) {
        return stateRepository.getByStateId(UUID.fromString(stateId));
    }

    @DeleteMapping("/states")
    public void removeBicycle() {
        stateRepository.deleteAllBy();
    }

    @DeleteMapping("/state/{stateId}")
    public void removeBicycle(@PathVariable String stateId) {
        stateRepository.deleteByStateId(UUID.fromString(stateId));
    }

    @PutMapping("/state")
    public State addLocation(@Valid @RequestBody State state) {
        return stateRepository.saveAndFlush(state);
    }

    @PostMapping("/state")
    public State updateLocation(@Valid @RequestBody State state) {
        return stateRepository.saveAndFlush(state);
    }
}
