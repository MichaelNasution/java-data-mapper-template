package service;

import mapper.BaseMapper;
import java.util.List;

/**
 * Generic base service class that wraps a mapper.
 * @param <T> The type of the entity.
 */
public abstract class BaseService<T> {
    protected final BaseMapper<T> mapper;

    protected BaseService(BaseMapper<T> mapper) {
        this.mapper = mapper;
    }

    public T getById(int id) {
        return mapper.findById(id);
    }

    public List<T> getAll() {
        return mapper.findAll();
    }

    public void save(T entity) {
        mapper.insert(entity);
    }

    public void update(T entity) {
        mapper.update(entity);
    }

    public void delete(int id) {
        mapper.delete(id);
    }
}
