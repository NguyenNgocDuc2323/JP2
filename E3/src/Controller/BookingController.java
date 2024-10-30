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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookingController implements IGeneric<Booking> {
    private List<Booking> bookings;
    public BookingController(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public RoomType bookTypeRoom(){
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
    public Booking bookRoom() {
        Scanner sc = new Scanner(System.in);
        RoomType roomType = bookTypeRoom();

        System.out.print("Enter Customer Name: ");
        String cusName = sc.nextLine().trim();
        System.out.print("Enter Customer Phone: ");
        String cusPhone = sc.nextLine().trim();
        System.out.print("Enter Room ID: ");
        String roomId = sc.nextLine().toUpperCase().trim();

        LocalDate checkIn = null;
        while (checkIn == null) {
            System.out.println("Enter Check In Date: (YYYY/MM/DD)");
            try {
                checkIn = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        LocalDate checkOut = null;
        while (checkOut == null) {
            System.out.println("Enter Check Out Date: (YYYY/MM/DD)");
            try {
                checkOut = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
        LocalDate finalCheckIn = checkIn;
        LocalDate finalCheckOut = checkOut;

        Booking foundBooking = bookings.stream()
                .filter(booking ->
                        booking.getRoom().getRoomType().equals(roomType) &&
                                booking.getCustomer().getCusName().toLowerCase().contains(cusName.toLowerCase()) &&
                                booking.getCustomer().getCusPhone().equals(cusPhone) &&
                                booking.getRoom().getId().equals(roomId) &&
                                booking.getCheckInDateTime().equals(finalCheckIn) &&
                                booking.getCheckOutDateTime().equals(finalCheckOut))
                .findFirst()
                .orElse(null);

        if (foundBooking == null) {
            System.out.println("No booking found with the given details.");
        } else {
            System.out.println("Booking found: " + foundBooking);
        }

        return foundBooking;
    }

    @Override
    public Map<RoomType, Double> calculateRevenueByRoomType(List<Booking> bookings) {
        return bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> booking.getRoom().getRoomType(),
                        Collectors.summingDouble(booking -> {
                            long days = ChronoUnit.DAYS.between(booking.getCheckInDateTime(), booking.getCheckOutDateTime());
                            long hours = days * 24;
                            return hours * booking.getRoom().getPricePerHour();
                        })
                ));
    }
}
