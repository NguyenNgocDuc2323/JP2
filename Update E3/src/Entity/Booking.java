package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
    private int id;
    private Customer customer;
    private Room room;
    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;
    public Booking() {;}

    public Booking(int id,Room room, Customer customer, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime) {
        this.id = id;
        this.customer = customer;
        this.room = room;
        this.checkInDateTime = checkInDateTime;
        this.checkOutDateTime = checkOutDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(LocalDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public LocalDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(LocalDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", room=" + room +
                ", checkInDateTime=" + checkInDateTime +
                ", checkOutDateTime=" + checkOutDateTime +
                '}';
    }
}
