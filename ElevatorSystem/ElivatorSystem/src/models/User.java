package models;

import models.enums.Direction;

public class User extends BaseClass{
    private  Floor floor;
    private Direction direction;
    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
