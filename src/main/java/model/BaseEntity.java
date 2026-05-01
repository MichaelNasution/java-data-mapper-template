package model;

/**
 * Kelas abstrak dasar untuk semua entitas domain.
 * Menyediakan properti dasar yang akan diwarisi oleh entitas lain.
 */
public abstract class BaseEntity {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
