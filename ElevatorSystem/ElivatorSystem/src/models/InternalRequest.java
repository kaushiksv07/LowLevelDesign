package models;

public class InternalRequest extends Request {
    private Elevator elevator;
    public InternalRequest(Floor floor, Elevator elevator) {
        super(floor);
        this.elevator = elevator;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }
}
