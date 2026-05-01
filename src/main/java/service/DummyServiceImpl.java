package service;

import exception.ServiceException;
import mapper.DummyMapper;
import model.DummyEntity;

import java.util.List;

/**
 * Professional implementation of DummyService.
 * Includes validation logic and architectural separation.
 */
public class DummyServiceImpl extends BaseService<DummyEntity> implements DummyService {

    public DummyServiceImpl() {
        super(new DummyMapper());
    }

    @Override
    public void save(DummyEntity entity) {
        validate(entity);
        try {
            super.save(entity);
        } catch (Exception e) {
            System.err.println("[SERVICE ERROR] Failed to save entity: " + e.getMessage());
            throw new ServiceException("Could not save dummy entity", e);
        }
    }

    @Override
    public void update(DummyEntity entity) {
        validate(entity);
        try {
            super.update(entity);
        } catch (Exception e) {
            System.err.println("[SERVICE ERROR] Failed to update entity: " + e.getMessage());
            throw new ServiceException("Could not update dummy entity", e);
        }
    }

    @Override
    public void printAllDummies() {
        System.out.println("[SERVICE] Fetching all entities for display...");
        List<DummyEntity> dummies = getAll();
        if (dummies.isEmpty()) {
            System.out.println("[SERVICE] No entities found.");
        } else {
            dummies.forEach(d -> System.out.println("  > " + d));
        }
    }

    /**
     * Business validation logic.
     * Ensures that data integrity is maintained before reaching the persistence layer.
     */
    private void validate(DummyEntity entity) {
        if (entity == null) {
            throw new ServiceException("Entity cannot be null");
        }
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            throw new ServiceException("Entity name is mandatory and cannot be blank");
        }
    }
}
