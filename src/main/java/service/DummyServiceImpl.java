package service;

import exception.ServiceException;
import mapper.DummyMapper;
import model.DummyEntity;

import java.util.List;

/**
 * Implementasi profesional dari DummyService.
 * Mencakup logika validasi dan pemisahan arsitektural.
 */
public class DummyServiceImpl extends BaseService<DummyEntity> implements DummyService {

    public DummyServiceImpl() {
        super(new DummyMapper());
    }

    @Override
    public void save(DummyEntity entitas) {
        validasi(entitas);
        try {
            super.save(entitas);
        } catch (Exception e) {
            System.err.println("[KESALAHAN LAYANAN] Gagal menyimpan entitas: " + e.getMessage());
            throw new ServiceException("Tidak dapat menyimpan entitas dummy", e);
        }
    }

    @Override
    public void update(DummyEntity entitas) {
        validasi(entitas);
        try {
            super.update(entitas);
        } catch (Exception e) {
            System.err.println("[KESALAHAN LAYANAN] Gagal memperbarui entitas: " + e.getMessage());
            throw new ServiceException("Tidak dapat memperbarui entitas dummy", e);
        }
    }

    @Override
    public void printAllDummies() {
        System.out.println("[LAYANAN] Mengambil semua entitas untuk ditampilkan...");
        List<DummyEntity> daftarDummy = getAll();
        if (daftarDummy.isEmpty()) {
            System.out.println("[LAYANAN] Tidak ada entitas yang ditemukan.");
        } else {
            for (DummyEntity d : daftarDummy) {
                System.out.println("  > " + d);
            }
        }
    }

    /**
     * Logika validasi bisnis.
     * Memastikan integritas data terjaga sebelum mencapai lapisan persistensi.
     */
    private void validasi(DummyEntity entitas) {
        if (entitas == null) {
            throw new ServiceException("Entitas tidak boleh null");
        }
        if (entitas.getNama() == null || entitas.getNama().trim().isEmpty()) {
            throw new ServiceException("Nama entitas wajib diisi dan tidak boleh kosong");
        }
    }
}
