package model;

/**
 * Implementasi konkret dari BaseEntity untuk tujuan demonstrasi.
 */
public class DummyEntity extends BaseEntity {
    private String nama;

    public DummyEntity() {}

    public DummyEntity(String nama) {
        this.nama = nama;
    }

    public DummyEntity(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "DummyEntity{id=" + id + ", nama='" + nama + "'}";
    }
}
