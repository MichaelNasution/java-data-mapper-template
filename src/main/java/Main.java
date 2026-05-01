import model.DummyEntity;
import service.DummyService;
import util.DBConnection;

import java.util.List;

/**
 * Main class to demonstrate the Data Mapper pattern and JDBC integration.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Java Data Mapper Template ===");

        DummyService dummyService = new DummyService();

        // 1. Insert dummy data
        System.out.println("\nInserting new dummy entities...");
        DummyEntity d1 = new DummyEntity("First Dummy");
        DummyEntity d2 = new DummyEntity("Second Dummy");
        
        dummyService.save(d1);
        dummyService.save(d2);
        
        System.out.println("Inserted: " + d1);
        System.out.println("Inserted: " + d2);

        // 2. Retrieve all data
        System.out.println("\nRetrieving all dummy entities from database:");
        List<DummyEntity> allDummies = dummyService.getAll();
        allDummies.forEach(System.out::println);

        // 3. Update data
        if (!allDummies.isEmpty()) {
            System.out.println("\nUpdating the first dummy entity...");
            DummyEntity toUpdate = allDummies.get(0);
            toUpdate.setName(toUpdate.getName() + " (Updated)");
            dummyService.update(toUpdate);
            System.out.println("Updated entity: " + dummyService.getById(toUpdate.getId()));
        }

        // 4. Delete data (optional demonstration)
        // System.out.println("\nDeleting an entity...");
        // dummyService.delete(d1.getId());

        System.out.println("\nFinal list of entities:");
        dummyService.printAllDummies();

        // Clean up connection
        DBConnection.closeConnection();
        System.out.println("\n=== Demonstration Completed ===");
    }
}
