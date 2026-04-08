import java.util.*;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    public void increment(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void display() {
        System.out.println("Inventory: " + inventory);
    }
}

class Reservation {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // reservationId → Reservation
        HashMap<String, Reservation> confirmed = new HashMap<>();

        // stack for rollback (LIFO)
        Stack<String> releasedRoomIds = new Stack<>();

        // simulate confirmed booking
        Reservation r1 = new Reservation("RES1", "Single Room");
        confirmed.put(r1.reservationId, r1);

        System.out.println("Before Cancellation:");
        inventory.display();

        String cancelId = "RES1";

        if (confirmed.containsKey(cancelId)) {

            Reservation res = confirmed.get(cancelId);

            // push room ID into rollback stack
            releasedRoomIds.push(cancelId);

            // restore inventory
            inventory.increment(res.roomType);

            // remove from confirmed bookings
            confirmed.remove(cancelId);

            System.out.println("Cancellation successful for " + cancelId);

        } else {
            System.out.println("Invalid cancellation request");
        }

        System.out.println("\nAfter Cancellation:");
        inventory.display();

        System.out.println("Rollback Stack: " + releasedRoomIds);
    }
}
