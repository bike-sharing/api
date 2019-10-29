package com.example.bikesharingapi.repository;

import com.example.bikesharingapi.models.Bicycle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BicycleRepository implements IRepository<Bicycle> {
    @Override
    public Bicycle get(int id) {
        return null;
    }

    public ArrayList<Bicycle> getAllAvailable(String latitude, String longitude, int radius) {
        return null;
    }

    public ArrayList<Bicycle> getAllAvailable(int locationId) {
        return null;
    }

    @Override
    public ArrayList<Bicycle> getAll() {
        return null;
    }

    @Override
    public void add(Bicycle value) {

    }

    @Override
    public void update(Bicycle value) {

    }

    @Override
    public void delete(int id) {

    }
}
