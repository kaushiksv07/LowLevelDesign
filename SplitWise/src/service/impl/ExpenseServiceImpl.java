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


    private ExpenseRepo expenseRepo;


    public ExpenseServiceImpl(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Override
    public void addExpenseDetails(Expense expense) {
        expenseRepo.save(expense);
    }
}
