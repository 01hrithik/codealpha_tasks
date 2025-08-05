import java.util.*;
import java.io.*;

public class HotelManager {
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;

    public HotelManager() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        loadRooms();
    }

    private void loadRooms() {
        for (int i = 1; i <= 5; i++) rooms.add(new Room("Standard", i));
        for (int i = 6; i <= 10; i++) rooms.add(new Room("Deluxe", i));
        for (int i = 11; i <= 15; i++) rooms.add(new Room("Suite", i));
    }

    public ArrayList<Room> getAvailableRooms(String category) {
        ArrayList<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked() && room.getCategory().equalsIgnoreCase(category)) {
                available.add(room);
            }
        }
        return available;
    }

    public boolean bookRoom(String name, String date, String category) {
        ArrayList<Room> available = getAvailableRooms(category);
        if (available.isEmpty()) return false;

        Room room = available.get(0);
        room.setBooked(true);
        bookings.add(new Booking(name, room, date));
        saveBookings();
        return true;
    }

    public boolean cancelBooking(String name) {
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getCustomerName().equalsIgnoreCase(name)) {
                booking.getRoom().setBooked(false);
                iterator.remove();
                saveBookings();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    private void saveBookings() {
        try (PrintWriter out = new PrintWriter("bookings.txt")) {
            for (Booking b : bookings) {
                out.println(b.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }
}
