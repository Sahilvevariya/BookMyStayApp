import java.util.*;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

class BookingRequest {
    String roomType;

    BookingRequest(String roomType) {
        this.roomType = roomType;
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        Queue<BookingRequest> queue = new LinkedList<>();

        queue.add(new BookingRequest("Single Room"));
        queue.add(new BookingRequest("Single Room"));
        queue.add(new BookingRequest("Double Room"));

        RoomInventory inventory = new RoomInventory();

        HashMap<String, Set<String>> allocated = new HashMap<>();

        while (!queue.isEmpty()) {

            BookingRequest request = queue.poll();

            if (inventory.getAvailability(request.roomType) > 0) {

                String roomId = request.roomType.substring(0,1) + UUID.randomUUID().toString().substring(0,4);

                allocated.putIfAbsent(request.roomType, new HashSet<>());
                allocated.get(request.roomType).add(roomId);

                inventory.decrement(request.roomType);

                System.out.println("Booking Confirmed → " + request.roomType + " | Room ID: " + roomId);

            } else {
                System.out.println("Booking Failed → No availability for " + request.roomType);
            }
        }
    }
}
