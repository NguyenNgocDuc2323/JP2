package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
    private int id;
    private Customer customer;
    private Room room;
    private LocalDate checkInDateTime;
    private LocalDate checkOutDateTime;
    public Booking() {;}

    public Booking(int id,Room room, Customer customer, LocalDate checkInDateTime, LocalDate checkOutDateTime) {
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

    public LocalDate getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(LocalDate checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public LocalDate getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(LocalDate checkOutDateTime) {
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
