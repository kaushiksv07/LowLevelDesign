package repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepo<T> {
    T save(T t);
    Optional<T> findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    void deleteAll();
}
