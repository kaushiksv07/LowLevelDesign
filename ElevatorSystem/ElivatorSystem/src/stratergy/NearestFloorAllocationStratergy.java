package stratergy;

import models.Elevator;
import models.User;
import models.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy to allocate the nearest elevator to a user
 * when all elevators are currently moving.
 *
 * The allocation logic:
 * - Only considers elevators moving in the same direction as the user.
 * - Elevator must be moving *towards* the user (not past them).
 * - Chooses the closest elevator among valid candidates.
 */
public class NearestFloorAllocationStratergy implements FloorAllocationStratergy{
    @Override
    public Elevator allocateFloorToElevator(User user, List<Elevator> elevators) {
        // We assume all elevators are currently in motion.

        // Get user's current floor number
        int userCurrentFloor = user.getFloor().getFloorDetails();

        // Get the direction in which the user wants to travel (UP or DOWN)
        Direction userDirection = user.getDirection();

        // Keeps track of the nearest elevator distance (difference in floors)
        int nearestFloor = Integer.MAX_VALUE;

        Elevator allocatedElevator = null;
        for(Elevator elevator : elevators){

            int currentFloorOfElevator = elevator.getCurrentFloor();

            Direction elevatorDirection = elevator.getDirection();

            int diff = Math.abs(userCurrentFloor-currentFloorOfElevator);

            int directionPoint = -1;

            // If the elevator is not moving in the same direction as the user, skip it
            if(userDirection != elevatorDirection){
                continue;
            }

            // Check if elevator is moving toward the user's floor
            // If user wants to go UP, elevator should be below user and moving up
            // If user wants to go DOWN, elevator should be above user and moving down
            if(elevatorDirection == Direction.DOWN && currentFloorOfElevator > userCurrentFloor){
                directionPoint = 1;
            }else if(elevatorDirection == Direction.UP && currentFloorOfElevator < userCurrentFloor){
                directionPoint = 1;
            }

            // If elevator is already on the same floor as the user — perfect match!
            if(currentFloorOfElevator == userCurrentFloor){
                return  elevator;
            }else if(diff < nearestFloor && directionPoint == 1){
                nearestFloor = diff;
                allocatedElevator = elevator;
            }
        }
        return allocatedElevator;
    }
}
