package repository.impl;

import model.BaseModel;
import repository.BaseRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseRepoImpl<T extends BaseModel> implements BaseRepo<T> {

    private Map<Long, T> db = new HashMap<>();
    private Long counter = 1L;

    @Override
    public T save(T entity) {
        if(entity.getId() == null || entity.getId() == 0L) {
            entity.setId(counter++);
        }
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<T> findAll() {
        return db.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<T> findById(long id) {
        T t = db.get(id);
        if(t != null) {
            return Optional.of(t);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {
        db.remove(id);
    }

    @Override
    public void deleteAll() {
        db.clear();
    }
}
