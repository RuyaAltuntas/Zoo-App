package com.zoo.zoo_app.model;

public class Habitat {
    private int HabitatID;
    private int Capacity;
    private String Habitat_Type;

    
    public Habitat(int HabitatID, int Capacity, String Habitat_Type) {
        this.HabitatID = HabitatID;
        this.Capacity = Capacity;
        this.Habitat_Type = Habitat_Type;
    }

    // getters
    public int getHabitatID() {
        return HabitatID;
    }

    public int getCapacity() {
        return Capacity;
    }

    public String getHabitat_Type() {
        return Habitat_Type;
    }

    // setters
    public void setHabitatId(int HabitatID) {
        this.HabitatID = HabitatID;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public void setHabitatType(String Habitat_Type) {
        this.Habitat_Type = Habitat_Type;
    }
}