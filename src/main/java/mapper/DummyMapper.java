package mapper;

import exception.DataAccessException;
import model.DummyEntity;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementasi Data Mapper untuk DummyEntity.
 * 
 * Mengapa Data Mapper?
 * Pola ini memisahkan objek domain dari skema database. DummyEntity tidak memiliki 
 * pengetahuan tentang SQL atau JDBC, memastikan model domain yang bersih.
 */
public class DummyMapper implements BaseMapper<DummyEntity> {

    // --- Konstanta SQL (Dikelompokkan secara Logis) ---
    private static final String CARI_BERDASARKAN_ID = "SELECT id, name FROM dummy WHERE id = ?";
    private static final String CARI_SEMUA           = "SELECT id, name FROM dummy";
    private static final String TAMBAH                = "INSERT INTO dummy (name) VALUES (?) RETURNING id";
    private static final String PERBARUI              = "UPDATE dummy SET name = ? WHERE id = ?";
    private static final String HAPUS                 = "DELETE FROM dummy WHERE id = ?";

    @Override
    public DummyEntity findById(int id) {
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement stmt = koneksi.prepareStatement(CARI_BERDASARKAN_ID)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetKeEntitas(rs) : null;
            }
        } catch (SQLException e) {
            logError("findById", e);
            throw new DataAccessException("Kesalahan saat mengambil entitas dengan ID: " + id, e);
        }
    }

    @Override
    public List<DummyEntity> findAll() {
        List<DummyEntity> daftarEntitas = new ArrayList<>();
        try (Connection koneksi = DBConnection.getConnection();
             Statement stmt = koneksi.createStatement();
             ResultSet rs = stmt.executeQuery(CARI_SEMUA)) {
            
            while (rs.next()) {
                daftarEntitas.add(mapResultSetKeEntitas(rs));
            }
        } catch (SQLException e) {
            logError("findAll", e);
            throw new DataAccessException("Kesalahan saat mengambil semua entitas", e);
        }
        return daftarEntitas;
    }

    @Override
    public void insert(DummyEntity entitas) {
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement stmt = koneksi.prepareStatement(TAMBAH)) {
            
            ikatParameter(stmt, entitas);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    entitas.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            logError("insert", e);
            throw new DataAccessException("Kesalahan saat menambah entitas: " + entitas.getNama(), e);
        }
    }

    @Override
    public void update(DummyEntity entitas) {
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement stmt = koneksi.prepareStatement(PERBARUI)) {
            
            ikatParameter(stmt, entitas);
            stmt.setInt(2, entitas.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logError("update", e);
            throw new DataAccessException("Kesalahan saat memperbarui entitas dengan ID: " + entitas.getId(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection koneksi = DBConnection.getConnection();
             PreparedStatement stmt = koneksi.prepareStatement(HAPUS)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logError("delete", e);
            throw new DataAccessException("Kesalahan saat menghapus entitas dengan ID: " + id, e);
        }
    }

    /**
     * Logika pengikatan parameter yang dipisahkan untuk keterbacaan.
     */
    private void ikatParameter(PreparedStatement stmt, DummyEntity entitas) throws SQLException {
        stmt.setString(1, entitas.getNama());
    }

    /**
     * Logika pemetaan terpusat.
     * Memastikan pembuatan objek yang konsisten dari hasil database.
     */
    private DummyEntity mapResultSetKeEntitas(ResultSet rs) throws SQLException {
        return new DummyEntity(
            rs.getInt("id"),
            rs.getString("name") // Kolom DB tetap 'name'
        );
    }

    private void logError(String operasi, SQLException e) {
        System.err.println("[KESALAHAN MAPPER] Operasi " + operasi + " gagal: " + e.getMessage());
    }
}
