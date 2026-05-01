package util;

import config.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas utilitas untuk mengelola koneksi database JDBC.
 * Mengimplementasikan manajemen koneksi dasar dan pelaporan kesalahan.
 */
public class DBConnection {
    private static Connection koneksi = null;

    /**
     * Mendapatkan koneksi database menggunakan DriverManager.
     *
     * @return Objek Connection
     * @throws SQLException jika terjadi kesalahan akses database
     */
    public static Connection getConnection() throws SQLException {
        if (koneksi == null || koneksi.isClosed()) {
            try {
                // Memastikan driver PostgreSQL dimuat
                Class.forName("org.postgresql.Driver");
                koneksi = DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USER,
                    DatabaseConfig.PASSWORD
                );
            } catch (ClassNotFoundException e) {
                System.err.println("[KESALAHAN DB] Driver JDBC PostgreSQL tidak ditemukan di classpath.");
                throw new SQLException("Driver JDBC PostgreSQL tidak ada", e);
            } catch (SQLException e) {
                System.err.println("[KESALAHAN DB] Gagal menghubungkan ke database di: " + DatabaseConfig.URL);
                throw e;
            }
        }
        return koneksi;
    }

    /**
     * Menutup koneksi database dengan aman.
     */
    public static void closeConnection() {
        if (koneksi != null) {
            try {
                koneksi.close();
                System.out.println("[DB] Koneksi berhasil ditutup.");
            } catch (SQLException e) {
                System.err.println("[KESALAHAN DB] Terjadi kesalahan saat menutup koneksi: " + e.getMessage());
            }
        }
    }
}
