package service.impl;

import models.Expense;
import models.ExpenseShare;
import models.Group;
import models.User;
import repository.GroupRepo;
import repository.UserRepo;
import service.ExpenseService;
import service.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    private GroupRepo groupRepo;


    public GroupServiceImpl(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @Override
    public Group createGroup(Group group) {
        return groupRepo.save(group);
    }

    @Override
    public List<User> getGroupMembers(Group group) {
        return groupRepo.getUsersFromGroup(group.getId());
    }
}
