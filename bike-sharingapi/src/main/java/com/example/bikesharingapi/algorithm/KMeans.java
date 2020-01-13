package com.example.bikesharingapi.algorithm;

import com.example.bikesharingapi.models.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.stream.Collectors.toList;

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

    private static Centroid nearestCentroid(Coordinates coordinates, List<Centroid> centroids, Distance distance) {
        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for(Centroid centroid : centroids) {
            double currentDistance = distance.calculate(coordinates, centroid.getCoordinates());

            if(currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = centroid;
            }
        }

        return nearest;
    }

    private static void assignToCluster(Map<Centroid, List<Coordinates>> clusters, Coordinates coordinates, Centroid centroid) {
        clusters.compute(centroid, (key, list) -> {
            if(list == null) {
                list = new ArrayList<>();
            }

            list.add(coordinates);
            return list;
        });
    }

    private static Centroid averageCentroid(Centroid centroid, List<Coordinates> coordinatesList) {
        if(coordinatesList.size() <= 1) {
            return centroid;
        }

        double x = 0.0;
        double y = 0.0;
        double z = 0.0;

        for(Coordinates coordinates : coordinatesList) {
            double latitude = coordinates.getLatitude() * Math.PI / 180;
            double longitude = coordinates.getLongitude() * Math.PI / 180;

            x += Math.cos(latitude) * Math.cos(longitude);
            y += Math.cos(latitude) * Math.sin(longitude);
            z += Math.sin(latitude);
        }

        int numberOfCoordinates = coordinatesList.size();

        x = x / numberOfCoordinates;
        y = y / numberOfCoordinates;
        z = z / numberOfCoordinates;

        double centerLongitude = Math.atan2(y, x);
        double centralSquareRoot = Math.sqrt(x * x + y * y);
        double centralLatitude = Math.atan2(z, centralSquareRoot);

        return new Centroid(new Coordinates(centerLongitude * 180 / Math.PI, centralLatitude * 180 / Math.PI));
    }

    private static List<Centroid> relocateCentroids(Map<Centroid, List<Coordinates>> clusters) {
        return clusters.entrySet().stream().map(e -> averageCentroid(e.getKey(), e.getValue())).collect(toList());
    }
}
