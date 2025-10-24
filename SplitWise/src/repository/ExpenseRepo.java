package repository;

import models.Expense;

import java.util.List;

public interface ExpenseRepo extends BaseRepository<Expense> {
    List<Expense> findByGroupId(long groupId);
}
