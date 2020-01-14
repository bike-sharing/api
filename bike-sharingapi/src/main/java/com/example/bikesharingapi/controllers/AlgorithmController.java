package com.example.bikesharingapi.controllers;

import com.example.bikesharingapi.algorithm.Result;
import com.example.bikesharingapi.algorithm.TestAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AlgorithmController {

    private ArrayList<Result> results;

    public AlgorithmController(){
        results = new TestAlgorithm().scenario(500, 10, 5000, 10);
    }

    @GetMapping("/algorithm")
    public ArrayList<Result> getResult(){
        return results;
    }
}
