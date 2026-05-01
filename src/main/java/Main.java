import model.DummyEntity;
import service.DummyService;
import service.DummyServiceImpl;
import util.DBConnection;

import java.util.List;

/**
 * Main application class (Client Layer).
 * 
 * Demonstrates the use of the architecturally mature Data Mapper foundation.
 * Note that this layer depends on the Service Interface, not the implementation.
 */
public class Main {
    public static void main(String[] args) {
        printHeader("JAVA DATA MAPPER - PROFESSIONAL FOUNDATION");

        // Dependency Inversion: Main depends on the Interface
        DummyService dummyService = new DummyServiceImpl();

        try {
            // 1. CREATE OPERATION
            System.out.println("[ACTION] Initializing dummy data...");
            DummyEntity first = new DummyEntity("Architectural Record");
            DummyEntity second = new DummyEntity("Mature Foundation");
            
            dummyService.save(first);
            dummyService.save(second);
            
            System.out.println("[STATUS] Data initialized successfully.\n");

            // 2. READ OPERATION (Demonstrating JCF visibility with explicit loops)
            System.out.println("[ACTION] Retrieving all records from database:");
            List<DummyEntity> allRecords = dummyService.getAll();
            
            // Professional iteration using enhanced for-loop (JCF Visibility)
            for (DummyEntity entity : allRecords) {
                System.out.println("  ID: " + entity.getId() + " | Name: " + entity.getName());
            }
            System.out.println();

            // 3. UPDATE OPERATION
            if (!allRecords.isEmpty()) {
                DummyEntity toUpdate = allRecords.get(0);
                System.out.println("[ACTION] Updating record ID " + toUpdate.getId() + "...");
                toUpdate.setName(toUpdate.getName() + " (Verified)");
                dummyService.update(toUpdate);
                
                DummyEntity updated = dummyService.getById(toUpdate.getId());
                System.out.println("[STATUS] Updated name: " + updated.getName() + "\n");
            }

            // 4. BUSINESS LOGIC DEMONSTRATION
            System.out.println("[ACTION] Demonstrating Service-level processing:");
            dummyService.printAllDummies();

        } catch (Exception e) {
            System.err.println("\n[CRITICAL ERROR] Application execution failed:");
            System.err.println("  Reason: " + e.getMessage());
        } finally {
            // Cleanup
            System.out.println("\n[SYSTEM] Shutting down...");
            DBConnection.closeConnection();
        }

        printFooter();
    }

    private static void printHeader(String title) {
        System.out.println("====================================================");
        System.out.println("   " + title);
        System.out.println("====================================================\n");
    }

    private static void printFooter() {
        System.out.println("\n====================================================");
        System.out.println("   DEMONSTRATION COMPLETED SUCCESSFULLY");
        System.out.println("====================================================");
    }
}
