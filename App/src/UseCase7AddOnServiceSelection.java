import java.util.*;

class AddOnService {
    String name;
    double price;

    AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        // reservationId → list of services
        HashMap<String, List<AddOnService>> serviceMap = new HashMap<>();

        String reservationId = "RES123";

        List<AddOnService> services = new ArrayList<>();

        // Guest selects services
        services.add(new AddOnService("Breakfast", 20));
        services.add(new AddOnService("Airport Pickup", 50));
        services.add(new AddOnService("Extra Bed", 30));

        // Map services to reservation
        serviceMap.put(reservationId, services);

        System.out.println("=== Add-On Services for " + reservationId + " ===");

        double totalCost = 0;

        for (AddOnService s : serviceMap.get(reservationId)) {
            System.out.println(s.name + " - $" + s.price);
            totalCost += s.price;
        }

        System.out.println("Total Add-On Cost: $" + totalCost);
    }
}
