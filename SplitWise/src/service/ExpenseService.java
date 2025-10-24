package service;

import models.Expense;
import models.ExpenseShare;

import java.util.List;

public interface ExpenseService {
    List<ExpenseShare> addExpenseDetails(Expense  expense);
}
