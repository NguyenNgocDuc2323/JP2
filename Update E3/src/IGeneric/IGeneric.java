package IGeneric;

import Entity.Booking;
import Entity.Room;
import Entity.RoomType;

import java.util.List;
import java.util.Map;

public interface IGeneric<T> {
    public T bookRoom(List<Room> rooms);
    public Map<RoomType,Double> calculateRevenueByRoomType(List<Booking> bookings);
    public Map<RoomType, Double> getMaxRevenueEntryFor2023(List<Booking> bookings);
}
