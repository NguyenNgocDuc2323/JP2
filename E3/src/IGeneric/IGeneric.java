package IGeneric;

import Entity.Booking;
import Entity.RoomType;

import java.util.List;
import java.util.Map;

public interface IGeneric<T> {
    public T bookRoom();
    public Map<RoomType,Double> calculateRevenueByRoomType(List<Booking> bookings);

}
