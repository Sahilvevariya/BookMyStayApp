import java.io.*;
import java.util.*;

class RoomInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}

class PersistenceService {

    public static void save(RoomInventory inventory) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("data.ser"))) {

            oos.writeObject(inventory);
            System.out.println("Data saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static RoomInventory load() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream("data.ser"))) {

            System.out.println("Data loaded successfully.");
            return (RoomInventory) ois.readObject();

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return new RoomInventory();
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        // Load previous state
        RoomInventory inventory = PersistenceService.load();

        inventory.display();

        // Simulate change
        inventory.inventory.put("Single Room", 1);

        // Save state
        PersistenceService.save(inventory);
    }
}
