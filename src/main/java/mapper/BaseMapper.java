package mapper;

import java.util.List;

/**
 * Generic interface for Data Mappers.
 * @param <T> The type of the entity.
 */
public interface BaseMapper<T> {
    T findById(int id);
    List<T> findAll();
    void insert(T entity);
    void update(T entity);
    void delete(int id);
}
