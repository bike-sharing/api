package com.example.bikesharingapi.algorithm;

public interface Distance {
    double calculate(double latitude1, double latitude2, double longitude1,
                     double longitude2, double altitude1, double altitude2);
}
