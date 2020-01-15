package com.example.bikesharingapi.algorithm;

import com.example.bikesharingapi.models.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestAlgorithm {

    private static Random random = new Random();

    public ArrayList<Result> scenario(int numberOfBicycles, int numberOfStations, int radius, int numberOfIterations) {
        KMeans kMeans = new KMeans();
        Distance distance = new EucledianDistance();
        List<Coordinates> bicycleCoordinates = randomBicycleLocations(new Coordinates(27.587200, 47.159809), numberOfBicycles, radius);
        Map<Centroid, List<Coordinates>> result = kMeans.fit(bicycleCoordinates, numberOfStations, distance, numberOfIterations);

        var algorithmResults = new ArrayList<Result>();
        var index = 0;

        for (Centroid centroid : result.keySet()) {
            System.out.println("Station: ");
            var algorithmResult = new Result();
            algorithmResult.setStationName("Station " + index);
            index ++;
            algorithmResult.setCoordinates(centroid.getCoordinates());
            System.out.println(centroid.getCoordinates().getLatitude());
            System.out.println(centroid.getCoordinates().getLongitude());
            for (int i = 0; i < result.get(centroid).size(); i++) {
                System.out.println("Bicycle: ");
                algorithmResult.getListOfBicycles().add(
                        new Coordinates(
                                result.get(centroid).get(i).getLongitude(),
                                result.get(centroid).get(i).getLatitude())
                );

                System.out.println(result.get(centroid).get(i).getLatitude());
                System.out.println(result.get(centroid).get(i).getLongitude());
            }
            System.out.println("====================================================");
            algorithmResults.add(algorithmResult);
        }

        return algorithmResults;
    }

    private static List<Coordinates> randomBicycleLocations(Coordinates townCenter, int k, int radius) {
        List<Coordinates> bicycles = new ArrayList<>();
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

            Coordinates coordinates = new Coordinates();

            coordinates.setLatitude(y + y0);
            coordinates.setLongitude(x + x0);

            bicycles.add(coordinates);
        }

        return bicycles;
    }
}
