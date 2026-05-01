import model.DummyEntity;
import service.DummyService;
import service.DummyServiceImpl;
import util.DBConnection;

import java.util.List;

/**
 * Kelas aplikasi utama (Lapisan Client).
 * 
 * Mendemonstrasikan penggunaan fondasi Data Mapper yang matang secara arsitektural.
 * Perhatikan bahwa lapisan ini bergantung pada Interface Service, bukan implementasinya.
 */
public class Main {
    public static void main(String[] args) {
        cetakHeader("JAVA DATA MAPPER - FONDASI PROFESIONAL");

        // Dependency Inversion: Main bergantung pada Interface
        DummyService dummyService = new DummyServiceImpl();

        try {
            // 1. OPERASI CREATE
            System.out.println("[LANGKAH 1] Inisialisasi data dummy...");
            DummyEntity pertama = new DummyEntity("Catatan Arsitektural");
            DummyEntity kedua = new DummyEntity("Fondasi yang Matang");
            
            dummyService.save(pertama);
            dummyService.save(kedua);
            
            System.out.println("[STATUS] Data berhasil diinisialisasi.\n");

            // 2. OPERASI READ (Mendemonstrasikan visibilitas JCF dengan loop eksplisit)
            System.out.println("[LANGKAH 2] Mengambil semua catatan dari database:");
            List<DummyEntity> semuaCatatan = dummyService.getAll();
            
            // Iterasi profesional menggunakan enhanced for-loop (Visibilitas JCF)
            for (DummyEntity entitas : semuaCatatan) {
                System.out.println("  ID: " + entitas.getId() + " | Nama: " + entitas.getNama());
            }
            System.out.println();

            // 3. OPERASI UPDATE
            if (!semuaCatatan.isEmpty()) {
                DummyEntity entitasDiperbarui = semuaCatatan.get(0);
                System.out.println("[LANGKAH 3] Memperbarui catatan ID " + entitasDiperbarui.getId() + "...");
                entitasDiperbarui.setNama(entitasDiperbarui.getNama() + " (Terverifikasi)");
                dummyService.update(entitasDiperbarui);
                
                DummyEntity hasilUpdate = dummyService.getById(entitasDiperbarui.getId());
                System.out.println("[STATUS] Nama terbaru: " + hasilUpdate.getNama() + "\n");
            }

            // 4. DEMONSTRASI LOGIKA BISNIS
            System.out.println("[LANGKAH 4] Demonstrasi pemrosesan tingkat Layanan:");
            dummyService.printAllDummies();

        } catch (Exception e) {
            System.err.println("\n[KESALAHAN KRITIS] Eksekusi aplikasi gagal:");
            System.err.println("  Alasan: " + e.getMessage());
        } finally {
            // Pembersihan
            System.out.println("\n[SISTEM] Mematikan sistem...");
            DBConnection.closeConnection();
        }

        cetakFooter();
    }

    private static void cetakHeader(String judul) {
        System.out.println("====================================================");
        System.out.println("   " + judul);
        System.out.println("====================================================\n");
    }

    private static void cetakFooter() {
        System.out.println("\n====================================================");
        System.out.println("   DEMONSTRASI BERHASIL DISELESAIKAN");
        System.out.println("====================================================");
    }
}
