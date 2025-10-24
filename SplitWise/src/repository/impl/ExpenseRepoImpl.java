package repository.impl;

import models.Expense;
import repository.BaseRepository;
import repository.ExpenseRepo;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseRepoImpl extends BaseRepositoryImpl<Expense> implements ExpenseRepo {
    @Override
    public List<Expense> findByGroupId(long groupId) {
        return this.findAll().stream()
                .filter(grp -> grp.getId() == groupId)
                .collect(Collectors.toList());
    }
}
