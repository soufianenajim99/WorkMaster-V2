package repositories.repositoryinterfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericRepository<T> {
    T save(T entity);
    Optional<T> findById(UUID id);
    List<T> findAll();
    T update(T entity);
    void deleteById(UUID id);
}
