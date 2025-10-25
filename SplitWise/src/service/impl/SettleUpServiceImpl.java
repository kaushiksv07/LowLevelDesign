package service.impl;

import models.Expense;
import models.Group;
import models.Transaction;
import models.User;
import repository.ExpenseRepo;
import repository.GroupRepo;
import service.SettleUpService;
import stratergy.SplitStratergy;

import java.security.InvalidParameterException;
import java.util.List;

public class SettleUpServiceImpl implements SettleUpService {

    private GroupRepo groupRepo;
    private ExpenseRepo expenseRepo;
    private SplitStratergy splitStratergy;

    public SettleUpServiceImpl(GroupRepo groupRepo, ExpenseRepo expenseRepo, SplitStratergy splitStratergy){
        this.groupRepo = groupRepo;
        this.expenseRepo = expenseRepo;
        this.splitStratergy = splitStratergy;
    }

    @Override
    public List<Transaction> settleUp(long groupId) {

        Group group = groupRepo.findById(groupId);
        if(group == null){
            throw new InvalidParameterException("Group not found");
        }
        List<User> users = group.getUsers();

        List<Expense> expenses = expenseRepo.findByGroupId(groupId);

        splitStratergy.split(expenses, group, users);
        return List.of();
    }
}
