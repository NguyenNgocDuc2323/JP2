package Controller;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import IGeneric.IGeneric;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class BookingController implements IGeneric<Booking> {
    private List<Booking> bookings;
    public BookingController(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public RoomType bookTypeRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Room Type: S(Single), D(Double), Q(Queen), QD(Quad), T(Triple): ");
        String roomTypeInput = sc.nextLine().toUpperCase();
        switch (roomTypeInput) {
            case "S":
                return RoomType.Single;
            case "D":
                return RoomType.Double;
            case "Q":
                return RoomType.Queen;
            case "QD":
                return RoomType.Quad;
            case "T":
                return RoomType.Triple;
            default:
                System.out.println("Invalid room type entered. Please try again.");
                return bookTypeRoom();
        }
    }
    @Override
    public Booking bookRoom(List<Room> rooms) {
        Scanner sc = new Scanner(System.in);
        RoomType roomType = bookTypeRoom();
        List<Customer> customers = List.of();
        LocalDateTime checkIn = null;
        while (checkIn == null) {
            System.out.println("Enter Check In Date: (YYYY/MM/DD HH:mm)");
            try {
                checkIn = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        LocalDateTime checkOut = null;
        while (checkOut == null) {
            System.out.println("Enter Check Out Date: (YYYY/MM/DD HH:mm)");
            try {
                checkOut = LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
        LocalDateTime finalCheckOut = checkOut;
        LocalDateTime finalCheckIn = checkIn;
        List<Room> availableRooms = rooms.stream()
                .filter(room -> room.getRoomType().equals(roomType))
                .filter(room -> bookings.stream()
                        .noneMatch(booking -> booking.getRoom().equals(room) &&
                                !(finalCheckOut.isBefore(booking.getCheckInDateTime()) || finalCheckIn.isAfter(booking.getCheckOutDateTime()))
                        ))
                .collect(Collectors.toList());
        if(availableRooms.isEmpty()) {
            System.out.println("No rooms available");
            System.exit(0);
        }
        else{
            System.out.println("+------------------------  Available Rooms --------------------------+ ");
            availableRooms.forEach(room -> {
                System.out.println(room);
            });
            System.out.println("+-------------------------------------------------------------------+ ");

        }
        System.out.print("Enter Customer Name: ");
        String cusName = sc.nextLine().trim();
        System.out.print("Enter Customer Phone: ");
        String cusPhone = sc.nextLine().trim();
        System.out.print("Enter Room ID: ");
        String roomId = sc.nextLine().toUpperCase().trim();

        Room selectedRoom = availableRooms.stream()
                .filter(room -> room.getId().equals(roomId))
                .findFirst()
                .orElse(null);

        if (selectedRoom == null) {
            System.out.println("Room ID not available or invalid.");
            return null;
        }
        int cusNumber = customers.size();
        int bookingNumber = bookings.size();
        Customer customer = new Customer(cusNumber,cusName, cusPhone);
        Booking newBooking = new Booking(bookingNumber,selectedRoom, customer, checkIn, checkOut);

        bookings.add(newBooking);

        System.out.println("Booking added successfully: " + newBooking);

        return newBooking;
    }


    @Override
    public Map<RoomType, Double> calculateRevenueByRoomType(List<Booking> bookings) {
        Map<RoomType, Double> revenueByRoomType = bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> booking.getRoom().getRoomType(),
                        Collectors.summingDouble(booking -> {
                            long days = ChronoUnit.DAYS.between(booking.getCheckInDateTime(), booking.getCheckOutDateTime());
                            return days * 24 * booking.getRoom().getPricePerHour();
                        })
                ));
        return revenueByRoomType;
    }

    @Override
    public Map<RoomType, Double> getMaxRevenueEntryFor2023(List<Booking> bookings) {
        Map<RoomType, Double> revenueByRoomTypeFor2023 = bookings.stream()
                .filter(booking -> booking.getCheckInDateTime().getYear() == 2023 && booking.getCheckOutDateTime().getYear() == 2023)
                .collect(Collectors.groupingBy(
                        booking -> booking.getRoom().getRoomType(),
                        Collectors.summingDouble(booking -> {
                            long days = ChronoUnit.DAYS.between(booking.getCheckInDateTime(), booking.getCheckOutDateTime());
                            return days * 24 * booking.getRoom().getPricePerHour();
                        })
                ));
        return revenueByRoomTypeFor2023.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(maxEntry -> Map.of(maxEntry.getKey(),maxEntry.getValue()))
                .orElse(Map.of());
    }
}
