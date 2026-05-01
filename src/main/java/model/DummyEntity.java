package model;

/**
 * A concrete implementation of BaseEntity for demonstration purposes.
 */
public class DummyEntity extends BaseEntity {
    private String name;

    public DummyEntity() {}

    public DummyEntity(String name) {
        this.name = name;
    }

    public DummyEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DummyEntity{id=" + id + ", name='" + name + "'}";
    }
}
