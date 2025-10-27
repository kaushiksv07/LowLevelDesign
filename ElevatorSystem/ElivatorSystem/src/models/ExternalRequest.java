package models;

import models.enums.Direction;

public class ExternalRequest extends Request{

    private User user;
    private Direction direction;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ExternalRequest(Floor floor, User  user, Direction direction) {
        super(floor);
        this.user = user;
        this.direction = direction;
    }
}
