package service;

import models.Expense;
import models.Transaction;

import java.util.List;

public interface SettleUpService {
    List<Transaction> settleUp(long groupId);
}
