package repository.impl;

import models.Group;
import models.User;
import repository.BaseRepository;
import repository.GroupRepo;

import java.util.List;
import java.util.stream.Collectors;

public class GroupRepoImpl extends BaseRepositoryImpl<Group> implements GroupRepo {
    @Override
    public List<User> getUsersFromGroup(long groupId) {
        return this.findAll().stream()
                .filter(grp -> grp.getId() == groupId)
                .flatMap(groupData -> groupData.getUsers().stream())
                .collect(Collectors.toList());
    }
}
