import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        Queue<Reservation> queue = new LinkedList<>();

        // Booking requests
        queue.add(new Reservation("Aakash", "Single Room"));
        queue.add(new Reservation("Rahul", "Double Room"));
        queue.add(new Reservation("Sneha", "Suite Room"));

        System.out.println("=== Booking Request Queue ===");

        // Display queue (FIFO order)
        for (Reservation r : queue) {
            System.out.println("Guest: " + r.guestName + 
                               " | Room: " + r.roomType);
        }

        System.out.println("\nNext request to process:");
        Reservation next = queue.peek();

        if (next != null) {
            System.out.println(next.guestName + 
                               " → " + next.roomType);
        }
    }
}
