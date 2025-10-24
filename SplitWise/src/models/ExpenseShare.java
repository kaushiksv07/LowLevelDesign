package models;

import models.enums.ExpenseType;

public class ExpenseShare extends BaseClass{
    private User user;
//    private Expense expense;
    private double amount;
    private ExpenseType expenseType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
