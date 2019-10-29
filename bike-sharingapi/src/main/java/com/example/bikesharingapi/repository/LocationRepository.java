package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Location;

import java.util.ArrayList;

public class LocationRepository implements IRepository<Location> {
    @Override
    public Location get(int id) {
        return null;
    }

    @Override
    public ArrayList<Location> getAll() {
        return null;
    }

    public ArrayList<Location> getAll(String latitude, String longitude, int radius) {
        return null;
    }

    @Override
    public void add(Location value) {

    }

    @Override
    public void update(Location value) {

    }

    @Override
    public void delete(int id) {

    }
}
