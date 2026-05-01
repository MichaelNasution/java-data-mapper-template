package service;

import mapper.DummyMapper;
import model.DummyEntity;

/**
 * Service implementation for DummyEntity.
 */
public class DummyService extends BaseService<DummyEntity> {

    public DummyService() {
        super(new DummyMapper());
    }

    // Additional business logic methods can be added here
    public void printAllDummies() {
        getAll().forEach(System.out::println);
    }
}
