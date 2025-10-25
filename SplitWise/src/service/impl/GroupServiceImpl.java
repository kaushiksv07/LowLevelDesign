package service.impl;

import models.Expense;
import models.ExpenseShare;
import models.Group;
import models.User;
import repository.GroupRepo;
import repository.UserRepo;
import service.ExpenseService;
import service.GroupService;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {

    private GroupRepo groupRepo;
    private UserRepo userRepo;

    public GroupServiceImpl(GroupRepo groupRepo, UserRepo userRepo) {
        this.groupRepo = groupRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Group createGroup(Group group) {
        List<User> users = group.getUsers();
        List<User> usersList = new ArrayList<User>();
        for(User user : users){
            User savedUser = userRepo.save(user);
            usersList.add(savedUser);
        }
        group.setUsers(usersList);
        return groupRepo.save(group);
    }

    @Override
    public List<User> getGroupMembers(Group group) {
        return groupRepo.getUsersFromGroup(group.getId());
    }
}
