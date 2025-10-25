package repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepo <T>{
    T save(T entity);
    List<T> findAll();
    Optional<T> findById(long id);
    void deleteById(long id);
    void deleteAll();
}
