package stratergy;

import models.Expense;
import models.ExpenseShare;
import models.Group;
import models.User;

import java.util.List;

public interface SplitStratergy {
    List<ExpenseShare> split(List<Expense> expenses, Group group, List<User> users);
}
