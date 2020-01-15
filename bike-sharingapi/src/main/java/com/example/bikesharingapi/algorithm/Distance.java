package com.example.bikesharingapi.algorithm;

import com.example.bikesharingapi.models.Coordinates;

public interface Distance {
    double calculate(Coordinates a, Coordinates b);
}
