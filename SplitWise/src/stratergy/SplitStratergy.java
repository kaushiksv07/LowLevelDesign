package stratergy;

import models.*;

import java.util.List;

public interface SplitStratergy {
    List<Transaction> split(List<Expense> expenses, Group group, List<User> users);
}
