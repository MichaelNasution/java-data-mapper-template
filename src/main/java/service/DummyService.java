package service;

import model.DummyEntity;
import java.util.List;

/**
 * Service contract for DummyEntity operations.
 * Defines the business behavior of the system.
 */
public interface DummyService {
    DummyEntity getById(int id);
    List<DummyEntity> getAll();
    void save(DummyEntity entity);
    void update(DummyEntity entity);
    void delete(int id);
    void printAllDummies();
}
