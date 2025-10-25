import models.*;
import repository.ExpenseRepo;
import repository.GroupRepo;
import repository.UserRepo;
import repository.impl.ExpenseRepoImpl;
import repository.impl.GroupRepoImpl;
import repository.impl.UserRepoImpl;
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

        // 1️⃣ Create Group and Users
        Group group = new Group();
        group.setName("Goa Trip");
        group.setDescription("Fun trip to Goa with friends");

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            User user = new User();
//            user.setId(i);
            user.setName("User" + i);
            user.setEmail("user" + i + "@mail.com");
            users.add(user);
        }
        group.setUsers(users);

        GroupRepo groupRepo = new GroupRepoImpl();
        UserRepo userRepo = new UserRepoImpl();
        GroupService groupService = new GroupServiceImpl(groupRepo, userRepo);
        Group createdGroup = groupService.createGroup(group);

        // 2️⃣ Create Expenses
        Expense expense1 = new Expense();
        expense1.setGroup(createdGroup);
        expense1.setExpenseDate(new Date());
        expense1.setAmount(1200);
//        expense1.setDescription("Dinner");
        expense1.setPaidUser(users.get(0)); // User1 paid ₹1200

        Expense expense2 = new Expense();
        expense2.setGroup(createdGroup);
        expense2.setExpenseDate(new Date());
        expense2.setAmount(800);
//        expense2.setDescription("Cab ride");
        expense2.setPaidUser(users.get(2)); // User3 paid ₹800

        Expense expense3 = new Expense();
        expense3.setGroup(createdGroup);
        expense3.setExpenseDate(new Date());
        expense3.setAmount(100);
//        expense2.setDescription("Cab ride");
        expense3.setPaidUser(users.get(0));


        Expense expense4 = new Expense();
        expense4.setGroup(createdGroup);
        expense4.setExpenseDate(new Date());
        expense4.setAmount(1000);
//        expense2.setDescription("Cab ride");
        expense4.setPaidUser(users.get(3));


        Expense expense5 = new Expense();
        expense5.setGroup(createdGroup);
        expense5.setExpenseDate(new Date());
        expense5.setAmount(1000);
//        expense2.setDescription("Cab ride");
        expense5.setPaidUser(users.get(1));


        // 3️⃣ Save Expenses via Repo/Service
        ExpenseRepo expenseRepo = new ExpenseRepoImpl();
        ExpenseService expenseService = new ExpenseServiceImpl(expenseRepo);
        expenseService.addExpenseDetails(expense1);
        expenseService.addExpenseDetails(expense2);
        expenseService.addExpenseDetails(expense3);
        expenseService.addExpenseDetails(expense4);
        expenseService.addExpenseDetails(expense5);

        List<Expense> expenses = new ArrayList<>();
        expenses.add(expense1);
        expenses.add(expense2);
        expenses.add(expense3);
        expenses.add(expense4);
        expenses.add(expense5);

        // 4️⃣ Apply Equal Split Strategy
        SplitStratergy splitStrategy = new EqualSplitStratergy();
        List<Transaction> transactions = splitStrategy.split(expenses, createdGroup, users);

        // 5️⃣ Print Group Details
        System.out.println("====== GROUP DETAILS ======");
        System.out.println("Group: " + createdGroup.getName());
        System.out.println("Members: ");
        for (User user : users) {
            System.out.println("  " + user.getName());
        }

        // 6️⃣ Print Expense Details
        System.out.println("\n====== EXPENSES ======");
        for (Expense e : expenses) {
            System.out.println(" - ₹" + e.getAmount() + " paid by " + e.getPaidUser().getName());
        }

        // 7️⃣ Print Transactions (Final Settlements)
        System.out.println("\n====== FINAL SETTLEMENTS ======");
        if (transactions.isEmpty()) {
            System.out.println("All settled up! 🎉");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t.getFromUser().getName() + " pays " +
                        t.getToUser().getName() + " ₹" + String.format("%.2f", t.getAmount()));
            }
        }
    }
}
