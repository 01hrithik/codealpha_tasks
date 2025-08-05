public class Booking {
    private String customerName;
    private Room room;
    private String date;

    public Booking(String customerName, Room room, String date) {
        this.customerName = customerName;
        this.room = room;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Name: " + customerName + ", Room: " + room.getNumber() + " (" + room.getCategory() + "), Date: " + date;
    }
}
