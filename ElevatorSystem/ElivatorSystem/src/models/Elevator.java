package models;

import models.enums.Direction;
import models.enums.Status;

import java.util.Queue;

public class Elevator extends BaseClass{
    private String name;
    private int minFloor;
    private int maxFloor;
    private int maxCapacity;
    private int currentFloor;

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    private Status status;
    private Direction direction;
    private Queue<InternalRequest> internalRequest;
    private Queue<ExternalRequest> externalRequest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public void setMinFloor(int minFloor) {
        this.minFloor = minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Queue<InternalRequest> getInternalRequest() {
        return internalRequest;
    }

    public void setInternalRequest(Queue<InternalRequest> internalRequest) {
        this.internalRequest = internalRequest;
    }

    public Queue<ExternalRequest> getExternalRequest() {
        return externalRequest;
    }

    public void setExternalRequest(Queue<ExternalRequest> externalRequest) {
        this.externalRequest = externalRequest;
    }
}
