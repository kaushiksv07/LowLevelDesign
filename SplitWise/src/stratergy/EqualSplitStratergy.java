package stratergy;

import models.*;
import models.enums.ExpenseType;

import java.util.*;

public class EqualSplitStratergy implements SplitStratergy{
    @Override
    public List<Transaction> split(List<Expense> expenses, Group group, List<User> users) {
        Map<User, Double> usersDts = new HashMap<>();

        for(Expense expense : expenses){
            double amount = expense.getAmount();//9 => 9/3 => 3 => but paid user it will be +9 -3
            double share = amount/users.size();
            User paidUser = expense.getPaidUser();
            for(User user : users){
                double curr = usersDts.getOrDefault(user, 0.0);
                if(user.getId() == paidUser.getId()){
                    usersDts.put(user, curr + (amount - share));
                }else{
                    usersDts.put(user, curr - share);
                }
            }
        }


        PriorityQueue<Balance> posiivePq = new PriorityQueue<>((a, b) -> Double.compare(b.getAmount(), a.getAmount()));
        PriorityQueue<Balance> negativePq = new PriorityQueue<>((a, b) -> Double.compare(a.getAmount(), b.getAmount()));

        for(Map.Entry<User, Double> entry : usersDts.entrySet()){
            Balance balance = new Balance();
            balance.setAmount(entry.getValue());
            balance.setUser(entry.getKey());
            if(entry.getValue() > 0){
                posiivePq.add(balance);
            }else{
                balance.setAmount(-1 * entry.getValue());
                negativePq.add(balance);
            }
        }


        List<Transaction> transactions = new ArrayList<>();
        while(posiivePq.size() > 0 && negativePq.size() > 0){

            Balance positiveBal = posiivePq.poll();
            Balance negativeBal = negativePq.poll();

            Transaction transaction = new Transaction();
            double settleAmt = Math.min(positiveBal.getAmount(), negativeBal.getAmount());
            if(positiveBal.getAmount() < negativeBal.getAmount()){
                transaction.setFromUser(negativeBal.getUser());
                transaction.setToUser(positiveBal.getUser());
//                transaction.setAmount(positiveBal.getAmount());

            }else {
                transaction.setFromUser(negativeBal.getUser());
                transaction.setToUser(positiveBal.getUser());
//                transaction.setAmount(negativeBal.getAmount());
            }
            transaction.setAmount(settleAmt);
            negativeBal.setAmount(negativeBal.getAmount() - settleAmt);
            positiveBal.setAmount(positiveBal.getAmount() - settleAmt);
            transactions.add(transaction);

            if(negativeBal.getAmount() > 0){
                negativePq.add(negativeBal);
            }
            if(positiveBal.getAmount() > 0){
                posiivePq.add(positiveBal);
            }
        }

        return transactions;
    }
}
