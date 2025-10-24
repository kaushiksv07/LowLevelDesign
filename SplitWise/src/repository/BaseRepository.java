package repository;

import java.util.List;

public interface BaseRepository<T> {
    T save(T t);
    T findById(Long id);
    List<T> findAll();
    void deleteById(Long id);
    void deleteAll();
}
