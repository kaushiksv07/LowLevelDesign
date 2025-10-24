package service.impl;

import exceptions.InvalidGroup;
import models.Expense;
import models.ExpenseShare;
import models.Group;
import models.User;
import repository.ExpenseRepo;
import repository.GroupRepo;
import service.ExpenseService;
import stratergy.EqualSplitStratergy;
import stratergy.SplitStratergy;

import java.util.ArrayList;
import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private GroupRepo groupRepo;
    private ExpenseRepo expenseRepo;
    private SplitStratergy splitStratergy;

    public ExpenseServiceImpl(GroupRepo groupRepo, SplitStratergy splitStratergy, ExpenseRepo expenseRepo) {
        this.groupRepo = groupRepo;
        this.expenseRepo = expenseRepo;
        this.splitStratergy = new EqualSplitStratergy();
    }

    @Override
    public List<ExpenseShare> addExpenseDetails(Expense expense) {
        expenseRepo.save(expense);

        long groupId = expense.getGroup().getId();
        List<Expense> expenseList = expenseRepo.findByGroupId(groupId);
        Group group = groupRepo.findById(groupId);
        if(group == null) {
            throw new InvalidGroup("Group with id " + groupId + " not found");
        }
        List<User> users = group.getUsers();
        return splitStratergy.split(expenseList, group, users);
    }
}
