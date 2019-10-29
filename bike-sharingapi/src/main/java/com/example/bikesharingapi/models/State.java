package com.example.bikesharingapi.models;

public class State {
    int id;
    int name;
    int description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public State(int id, int name, int description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
