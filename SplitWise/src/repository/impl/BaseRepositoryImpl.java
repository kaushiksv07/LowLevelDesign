package repository.impl;

import models.BaseClass;
import repository.BaseRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRepositoryImpl<T extends BaseClass> implements BaseRepository<T> {

    private final Map<Long, T> database =  new HashMap<>();
    private long counter = 1L;

    @Override
    public T save(T t) {
        if (t.getId() == null || t.getId() == 0L) {
            t.setId(counter++);
        }
        database.put(t.getId(), t);
        return t;
    }

    @Override
    public T findById(Long id) {
        return database.get(id);
    }

    @Override
    public List<T> findAll() {
        return  new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }

    @Override
    public void deleteAll() {
        database.clear();
    }
}
