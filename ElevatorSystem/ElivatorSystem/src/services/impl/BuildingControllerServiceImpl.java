package services.impl;

import exception.ElevatorNotFound;
import models.Elevator;
import models.User;
import models.enums.Status;
import repository.ElevatorRepo;
import services.BuildingControllerService;
import stratergy.FloorAllocationStratergy;
import models.ExternalRequest;
import stratergy.IdleElevatorStratergy;
import stratergy.NearestFloorAllocationStratergy;

import java.util.ArrayList;
import java.util.List;


public class BuildingControllerServiceImpl implements BuildingControllerService {

    private ElevatorRepo elevatorRepo;
    private IdleElevatorStratergy idleElevatorStratergy;
    private NearestFloorAllocationStratergy nearestFloorAllocationStratergy;

    public BuildingControllerServiceImpl(ElevatorRepo elevatorRepo) {
        this.idleElevatorStratergy = new IdleElevatorStratergy();
        this.nearestFloorAllocationStratergy = new NearestFloorAllocationStratergy();
        this.elevatorRepo = elevatorRepo;
    }

    @Override
    public Elevator handleExteranlRequests(ExternalRequest request, User user) {

        List<Elevator> listOfIdleElevator = new ArrayList<>();
        List<Elevator> listOfMovingElevator = new ArrayList<>();

        List<Elevator> elevators = elevatorRepo.findAll();

        if(elevators.isEmpty()){
            throw new ElevatorNotFound("Elevator not found");
        }

        for(Elevator elevator : elevators){
            if(elevator.getCurrentStatus() == Status.IDLE){
                listOfIdleElevator.add(elevator);
            }else if(elevator.getCurrentStatus() == Status.MOVING){
                listOfMovingElevator.add(elevator);
            }
        }


        // Case 1: All elevators are idle
        if (!listOfIdleElevator.isEmpty() && listOfMovingElevator.isEmpty()) {
            return idleElevatorStratergy.allocateFloorToElevator(user, listOfIdleElevator);
        }

        // Case 2: All elevators are moving
        if (listOfIdleElevator.isEmpty() && !listOfMovingElevator.isEmpty()) {
            return nearestFloorAllocationStratergy.allocateFloorToElevator(user, listOfMovingElevator);
        }

        // Case 3: Mix of idle and moving elevators — pick the better one
        if(! listOfIdleElevator.isEmpty() && !listOfMovingElevator.isEmpty()){
            Elevator bestIdleElevator = idleElevatorStratergy.allocateFloorToElevator(user, listOfIdleElevator);
            Elevator bestMovindElevator = nearestFloorAllocationStratergy.allocateFloorToElevator(user, listOfMovingElevator);

            if (Math.abs(bestIdleElevator.getCurrentFloor() - user.getFloor().getFloorDetails())
                    <= Math.abs(bestMovindElevator.getCurrentFloor() - user.getFloor().getFloorDetails())) {
                return bestIdleElevator;
            }else{
                return bestMovindElevator;
            }
        }

        // Case 4: No matching elevators
        throw new ElevatorNotFound("No suitable elevator found for the request");
    }
}
