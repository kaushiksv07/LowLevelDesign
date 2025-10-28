package services;

import models.*;

public interface ElevatorService {

    void addInternalRequest(Elevator elevator, InternalRequest request);
    void addExternalRequest(ExternalRequest request);
    void moveElevator(Elevator elevator);
    boolean canAcceptRequest(Elevator elevator, ExternalRequest request);
    void updateStatus(Elevator elevator);
    void updateDirection(Elevator elevator);
    void processNextRequest(Elevator elevator);
}
