package model;

import model.enums.SeatType;

public class Seat extends  BaseModel{
    private String name;
    private SeatType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }
}
