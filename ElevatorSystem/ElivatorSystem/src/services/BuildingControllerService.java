package services;

import models.Elevator;
import models.User;
import models.ExternalRequest;


public interface BuildingControllerService {
    Elevator handleExteranlRequests(ExternalRequest request, User user);
}
