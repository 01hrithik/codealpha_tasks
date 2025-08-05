public class Room {
    private int number;
    private String category;
    private boolean booked;

    public Room(String category, int number) {
        this.category = category;
        this.number = number;
        this.booked = false;
    }

    public String getCategory() {
        return category;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return number + " (" + category + ")";
    }
}
