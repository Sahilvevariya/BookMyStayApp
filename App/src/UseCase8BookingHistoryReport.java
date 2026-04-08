import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {

    public static void generateReport(List<Reservation> history) {

        System.out.println("=== Booking History Report ===");

        for (Reservation r : history) {
            System.out.println(
                "ID: " + r.reservationId +
                " | Guest: " + r.guestName +
                " | Room: " + r.roomType
            );
        }

        System.out.println("Total Bookings: " + history.size());
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Simulated confirmed bookings
        history.addReservation(new Reservation("RES1", "Aakash", "Single Room"));
        history.addReservation(new Reservation("RES2", "Rahul", "Double Room"));
        history.addReservation(new Reservation("RES3", "Sneha", "Suite Room"));

        // Generate report
        BookingReportService.generateReport(history.getHistory());
    }
}
