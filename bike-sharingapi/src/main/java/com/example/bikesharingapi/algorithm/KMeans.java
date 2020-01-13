package com.example.bikesharingapi.algorithm;

import com.example.bikesharingapi.models.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KMeans implements Algorithm {
    private static Random random = new Random();

    @Override
    public Map<Centroid, List<Coordinates>> fit(List<Coordinates> coordinatesList, int k, Distance distance, int maxIterations) {
        return null;
    }

    private static List<Centroid> randomCentroids(Coordinates townCenter, int k, int radius) {
        List<Centroid> centroids = new ArrayList<>();
        double rd = radius / 111300d;
        double t, u, v, w, x, y, x0, y0;

        y0 = townCenter.getLongitude();
        x0 = townCenter.getLatitude();

        for(int i = 0; i < k; i++) {
            u = random.nextDouble();
            v = random.nextDouble();

            w = rd * Math.sqrt(u);
            t = 2 * Math.PI * v;
            x = w * Math.cos(t);
            y = w * Math.sin(t);

            Centroid centroid = new Centroid();
            Coordinates coordinates = new Coordinates();

            coordinates.setLatitude(y + y0);
            coordinates.setLongitude(x + x0);
            centroid.setCoordinates(coordinates);

            centroids.add(centroid);
        }

        return centroids;
    }
}
