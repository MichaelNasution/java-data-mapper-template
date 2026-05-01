package mapper;

import java.util.List;

/**
 * Generic interface for Data Mappers.
 * 
 * Why Layering?
 * This interface defines a contract for persistence, allowing the service layer
 * to remain agnostic of the underlying storage technology (e.g., JDBC, File, etc.).
 * 
 * @param <T> The type of the domain entity.
 */
public interface BaseMapper<T> {
    T findById(int id);
    List<T> findAll();
    void insert(T entity);
    void update(T entity);
    void delete(int id);
}
