package stratergy;

import models.Elevator;
import models.Floor;
import models.User;

import java.util.List;

public class IdleElevatorStratergy implements FloorAllocationStratergy{

    // in this case we are expecting all idle elevators are passed
    @Override
    public Elevator allocateFloorToElevator(User user, List<Elevator> elevators) {
        Floor floor = user.getFloor();
        int floorDetails = floor.getFloorDetails();
        int nearestFloor = Integer.MAX_VALUE;
        Elevator allocatedElevator = null;
        for(Elevator elevator : elevators){
            int currentFloorOfElevator = elevator.getCurrentFloor();
            int diff = Math.abs(floorDetails-currentFloorOfElevator);
            if(diff<nearestFloor){
                nearestFloor =  diff;
                allocatedElevator = elevator;
            }
        }
        System.out.println("Allocated Elevator: " + allocatedElevator.getName());
        return allocatedElevator;
    }
}
