package repository;

import models.Group;
import models.User;

import java.util.List;

public interface GroupRepo extends BaseRepository<Group> {
    List<User> getUsersFromGroup(long groupId);
}
