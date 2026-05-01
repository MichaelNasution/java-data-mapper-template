package mapper;

import exception.DataAccessException;
import model.DummyEntity;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Mapper implementation for DummyEntity.
 * 
 * Why Data Mapper?
 * It isolates the domain objects from the database schema. The DummyEntity
 * has no knowledge of SQL or JDBC, ensuring a clean domain model.
 */
public class DummyMapper implements BaseMapper<DummyEntity> {

    // --- SQL Constants (Logically Grouped) ---
    private static final String FIND_BY_ID = "SELECT id, name FROM dummy WHERE id = ?";
    private static final String FIND_ALL   = "SELECT id, name FROM dummy";
    private static final String INSERT     = "INSERT INTO dummy (name) VALUES (?) RETURNING id";
    private static final String UPDATE     = "UPDATE dummy SET name = ? WHERE id = ?";
    private static final String DELETE     = "DELETE FROM dummy WHERE id = ?";

    @Override
    public DummyEntity findById(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToEntity(rs) : null;
            }
        } catch (SQLException e) {
            logError("findById", e);
            throw new DataAccessException("Error retrieving entity with ID: " + id, e);
        }
    }

    @Override
    public List<DummyEntity> findAll() {
        List<DummyEntity> entities = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(FIND_ALL)) {
            
            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            logError("findAll", e);
            throw new DataAccessException("Error retrieving all entities", e);
        }
        return entities;
    }

    @Override
    public void insert(DummyEntity entity) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT)) {
            
            bindParameters(stmt, entity);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            logError("insert", e);
            throw new DataAccessException("Error inserting entity: " + entity.getName(), e);
        }
    }

    @Override
    public void update(DummyEntity entity) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
            
            bindParameters(stmt, entity);
            stmt.setInt(2, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logError("update", e);
            throw new DataAccessException("Error updating entity with ID: " + entity.getId(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logError("delete", e);
            throw new DataAccessException("Error deleting entity with ID: " + id, e);
        }
    }

    /**
     * Parameter binding logic decomposed for readability.
     */
    private void bindParameters(PreparedStatement stmt, DummyEntity entity) throws SQLException {
        stmt.setString(1, entity.getName());
    }

    /**
     * Centralized mapping logic.
     * Ensures consistent object creation from database results.
     */
    private DummyEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new DummyEntity(
            rs.getInt("id"),
            rs.getString("name")
        );
    }

    private void logError(String operation, SQLException e) {
        System.err.println("[MAPPER ERROR] " + operation + " failed: " + e.getMessage());
    }
}
