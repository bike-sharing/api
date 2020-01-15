package com.example.bikesharingapi.algorithm;

import com.example.bikesharingapi.models.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.Random;

public interface Algorithm {
    Map<Centroid, List<Coordinates>> fit(List<Coordinates> coordinatesList, int k, Distance distance, int maxIterations);
}
