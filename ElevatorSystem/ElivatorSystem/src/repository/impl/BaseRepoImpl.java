package repository.impl;

import models.BaseClass;
import repository.BaseRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseRepoImpl<T extends BaseClass> implements BaseRepo<T> {

    private final Map<Long, T> db = new HashMap<>();
    private  long counter = 1L;

    @Override
    public T save(T t) {
        if(t.getId() == null || t.getId() == 0L) {
            t.setId(counter++);
        }
        db.put(t.getId(), t);
        return t;
    }

    @Override
    public Optional<T> findById(Long id) {
        T t =  db.get(id);
        if(t == null) {
            return Optional.empty();
        }
        return Optional.of(t);
    }

    @Override
    public List<T> findAll() {
        return db.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        db.remove(id);
    }

    @Override
    public void deleteAll() {
        db.clear();
    }
}
