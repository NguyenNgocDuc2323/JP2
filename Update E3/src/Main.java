import Controller.BookingController;
import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();
        BookingController bookingController = new BookingController(bookings);
        rooms.add(new Room("RS001", RoomType.Single,8));
        rooms.add(new Room("RS002", RoomType.Single,10));
        rooms.add(new Room("RS003", RoomType.Single,15));
        rooms.add(new Room("RD001", RoomType.Double,12));
        rooms.add(new Room("RQ002", RoomType.Queen,35));
        rooms.add(new Room("RT001", RoomType.Triple,12.5));
        rooms.add(new Room("RQ002", RoomType.Quad,20.5));

        customers.add(new Customer(1,"Mr.Linus Tovaldo","84125325345457"));
        customers.add(new Customer(2,"Mr.Bill","91124235346467"));
        customers.add(new Customer(3,"Mr.Turing","911242353464"));

        bookings.add(new Booking(1, rooms.get(0), customers.get(0),
                LocalDateTime.of(2023, 03, 15, 13,15,0),
                LocalDateTime.of(2023, 03, 16,13,15,0)
        ));

        bookings.add(new Booking(2, rooms.get(0), customers.get(1),
                LocalDateTime.of(2024, 06, 9,10,0,0),
                LocalDateTime.of(2024, 06, 10,10,10,0)
        ));

        bookings.add(new Booking(3, rooms.get(1), customers.get(1),
                LocalDateTime.of(2024, 03, 11,23,30,0),
                LocalDateTime.of(2024, 03, 13,23,30,0)
        ));

        bookings.add(new Booking(4, rooms.get(3), customers.get(2),
                LocalDateTime.of(2023, 11, 11,22,15,0),
                LocalDateTime.of(2023, 11, 13,22,15,0)
        ));

        bookings.add(new Booking(5, rooms.get(3), customers.get(0),
                LocalDateTime.of(2023, 10, 25,14,15,0),
                LocalDateTime.of(2023, 10, 26,14,15,0)
        ));

        bookings.add(new Booking(6, rooms.get(4), customers.get(0),
                LocalDateTime.of(2024, 8, 18,20,45,0),
                LocalDateTime.of(2024, 8, 19,20,45,0)
        ));
//        3.1 :
        Booking booked = bookingController.bookRoom(rooms);
        System.out.println("Your Booking : " + booked);

//        3.2 :
        Map<RoomType, Double> listRevenueByRoomType = bookingController.calculateRevenueByRoomType( bookings);

        listRevenueByRoomType.forEach((roomType, totalRevenue) ->
                System.out.println("Room Type: " + roomType + ", Total Revenue: " + totalRevenue + "$")
        );

//        3.3 :
        Map<RoomType, Double> maxRevenueEntry = bookingController.getMaxRevenueEntryFor2023(bookings);
        if (maxRevenueEntry != null && !maxRevenueEntry.isEmpty()) {
            Map.Entry<RoomType, Double> entry = maxRevenueEntry
                    .entrySet()
                    .stream()
                    .findFirst()
                    .orElse(null);
            RoomType maxRoomType = entry.getKey();
            Double maxRevenue = entry.getValue();
            System.out.println("\nMax Revenue Room Type: " + maxRoomType + ", Total Revenue: " + maxRevenue + "$");
        } else {
            System.out.println("No bookings available.");
        }

    }
}