package edu.upc.dsa.models;

public class Trademark {
    String name;
    String id;
    int appliedVaccines;

    public Trademark(String name, String id) {
        this.name = name;
        this.id = id;
        this.appliedVaccines = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAppliedVaccines() {
        return appliedVaccines;
    }

    public void setAppliedVaccines(int appliedVaccines) {
        this.appliedVaccines = appliedVaccines;
    }
}
