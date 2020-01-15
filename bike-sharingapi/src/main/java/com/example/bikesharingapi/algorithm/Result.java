package com.example.bikesharingapi.algorithm;

import com.example.bikesharingapi.models.Coordinates;

import java.util.ArrayList;

public class Result {
    String stationName;
    Coordinates coordinates;

    ArrayList<Coordinates> listOfBicycles;

    public Result() {
        coordinates = new Coordinates();
        listOfBicycles = new ArrayList<>();
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Coordinates> getListOfBicycles() {


        return listOfBicycles;
    }

    public void setListOfBicycles(ArrayList<Coordinates> listOfBicycles) {
        this.listOfBicycles = listOfBicycles;
    }
}
