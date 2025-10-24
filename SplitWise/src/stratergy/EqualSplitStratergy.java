package stratergy;

import models.Expense;
import models.ExpenseShare;
import models.Group;
import models.User;
import models.enums.ExpenseType;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStratergy implements SplitStratergy{
    @Override
    public List<ExpenseShare> split(List<Expense> expenses, Group group, List<User> users) {
        for(Expense expense : expenses) {

        }
        double amount = expense.getAmount();
        List<ExpenseShare> expenseShares = new ArrayList<>();
        int userCount = users.size();
        double splitAmount = amount/userCount;
        User paidUser = expense.getPaidUser();
        for(int i = 0; i < userCount; i++){
            ExpenseShare  expenseShare = new ExpenseShare();
            expenseShare.setUser(users.get(i));
            expenseShare.setAmount(splitAmount);
            if(paidUser.getId() == users.get(i).getId()){
                expenseShare.setExpenseType(ExpenseType.PAID);
            }else{
                expenseShare.setExpenseType(ExpenseType.OWED);
            }
            expenseShares.add(expenseShare);
        }
        return expenseShares;
    }
}
