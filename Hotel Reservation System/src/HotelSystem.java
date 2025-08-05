import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HotelSystem extends JFrame {
    private HotelManager manager;

    private JTextField nameField;
    private JTextField dateField;
    private JComboBox<String> categoryBox;
    private JTextArea outputArea;

    public HotelSystem() {
        manager = new HotelManager();

        setTitle("Hotel Reservation System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Room Category:"));
        categoryBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
        inputPanel.add(categoryBox);

        JButton bookBtn = new JButton("Book Room");
        JButton cancelBtn = new JButton("Cancel Booking");
        inputPanel.add(bookBtn);
        inputPanel.add(cancelBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Center panel
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        JButton viewBtn = new JButton("View All Bookings");
        JButton searchBtn = new JButton("Search Available Rooms");

        bottomPanel.add(viewBtn);
        bottomPanel.add(searchBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        bookBtn.addActionListener(e -> bookRoom());
        cancelBtn.addActionListener(e -> cancelBooking());
        viewBtn.addActionListener(e -> viewBookings());
        searchBtn.addActionListener(e -> searchRooms());

        setVisible(true);
    }

    private void bookRoom() {
        String name = nameField.getText();
        String date = dateField.getText();
        String category = (String) categoryBox.getSelectedItem();

        if (manager.bookRoom(name, date, category)) {
            outputArea.setText("Booking successful!\n");
        } else {
            outputArea.setText("Booking failed. No rooms available.\n");
        }
    }

    private void cancelBooking() {
        String name = nameField.getText();
        if (manager.cancelBooking(name)) {
            outputArea.setText("Booking cancelled.\n");
        } else {
            outputArea.setText("No booking found under that name.\n");
        }
    }

    private void viewBookings() {
        ArrayList<Booking> bookings = manager.getBookings();
        if (bookings.isEmpty()) {
            outputArea.setText("No bookings found.\n");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Booking b : bookings) {
            sb.append(b.toString()).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void searchRooms() {
        String category = (String) categoryBox.getSelectedItem();
        ArrayList<Room> available = manager.getAvailableRooms(category);
        if (available.isEmpty()) {
            outputArea.setText("No available rooms in " + category + " category.\n");
        } else {
            StringBuilder sb = new StringBuilder("Available Rooms in " + category + ":\n");
            for (Room room : available) {
                sb.append("Room ").append(room.getNumber()).append("\n");
            }
            outputArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        new HotelSystem();
    }
}
