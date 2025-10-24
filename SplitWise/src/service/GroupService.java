package service;

import models.Group;
import models.User;

import java.util.List;

public interface GroupService {
    Group createGroup(Group group);
    List<User> getGroupMembers(Group group);
}
