import java.util.*;

class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
    }

    public synchronized boolean bookRoom(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}

class BookingTask implements Runnable {

    private RoomInventory inventory;
    private String guestName;

    BookingTask(RoomInventory inventory, String guestName) {
        this.inventory = inventory;
        this.guestName = guestName;
    }

    @Override
    public void run() {

        synchronized (inventory) {

            boolean success = inventory.bookRoom("Single Room");

            if (success) {
                System.out.println("Booking SUCCESS for " + guestName);
            } else {
                System.out.println("Booking FAILED for " + guestName);
            }
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Thread t1 = new Thread(new BookingTask(inventory, "Aakash"));
        Thread t2 = new Thread(new BookingTask(inventory, "Rahul"));
        Thread t3 = new Thread(new BookingTask(inventory, "Sneha"));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.display();
    }
}
