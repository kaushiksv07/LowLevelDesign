package stratergy;

import models.Elevator;
import models.User;

import java.util.List;

public interface FloorAllocationStratergy {
    Elevator allocateFloorToElevator(User user, List<Elevator> elevators);
}
