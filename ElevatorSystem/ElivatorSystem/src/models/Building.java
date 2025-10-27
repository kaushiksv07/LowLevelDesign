package models;

import java.util.List;

public class Building extends BaseClass{
    private String name;
    private List<Elevator> elevators;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public void setElevators(List<Elevator> elevators) {
        this.elevators = elevators;
    }
}
