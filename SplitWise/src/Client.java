import models.Expense;
import models.ExpenseShare;
import models.Group;
import models.User;
import repository.GroupRepo;
import repository.impl.GroupRepoImpl;
import service.ExpenseService;
import service.GroupService;
import service.impl.ExpenseServiceImpl;
import service.impl.GroupServiceImpl;
import stratergy.EqualSplitStratergy;
import stratergy.SplitStratergy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client {
    public static void main(String[] args) {

        //1 Creating groups and user
        Group group = new Group();
        group.setName("Goa trip");
        group.setDescription("Goa trip");
        List<User> users = new ArrayList<User>();
        for(int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("user" + i);
            user.setEmail("email" + i);
            user.setPassword("password" + i);
            users.add(user);
        }
        group.setUsers(users);
        GroupRepo groupRepo = new GroupRepoImpl();
        GroupService groupService = new GroupServiceImpl(groupRepo);

        Group createdGroup = groupService.createGroup(group);
        // Add Expense to goa trip

        Expense expense = new Expense();
        expense.setGroup(createdGroup);
        expense.setExpenseDate(new Date());
        expense.setAmount(1000);
        expense.setPaidUser(users.get(5));


        Expense expense2 = new Expense();
        expense2.setGroup(createdGroup);
        expense2.setExpenseDate(new Date());
        expense2.setAmount(2000);
        expense2.setPaidUser(users.get(3));

        SplitStratergy splitStratergy = new EqualSplitStratergy();
        ExpenseService expenseService = new ExpenseServiceImpl(groupRepo, splitStratergy);

        List<ExpenseShare> expenseShareList = expenseService.addExpenseDetails(expense);
        List<ExpenseShare> expenseShareList = expenseService.addExpenseDetails(expense);

        System.out.println("Expense Split Details for Group: " + createdGroup.getName());
        System.out.println("Total Expense: " + expense.getAmount());
        System.out.println("Paid By: " + expense.getPaidUser().getName());
        System.out.println("--------------------------------------------------");
        System.out.printf("%-15s %-10s %-10s%n", "User", "Amount", "Status");
        System.out.println("--------------------------------------------------");

        for (ExpenseShare expenseShare : expenseShareList) {
            String userName = expenseShare.getUser().getName();
            double amount = expenseShare.getAmount();
            String status = expenseShare.getExpenseType().toString(); // PAID or OWED
            System.out.printf("%-15s %-10.2f %-10s%n", userName, amount, status);
        }
        System.out.println("--------------------------------------------------");


    }
}
