package com.example.bikesharingapi.repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IRepository<T> {

    T get(int id);

    ArrayList<T> getAll();

    void add(T value);

    void update(T value);

    void delete(int id);
}
