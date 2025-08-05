import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelSystemGUI extends JFrame {
    private HotelManager manager = new HotelManager();

    private JTextField nameField = new JTextField(10);
    private JTextField dateField = new JTextField(10);
    private JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
    private JTextArea outputArea = new JTextArea(10, 30);

    public HotelSystemGUI() {
        setTitle("Hotel Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Booking Panel
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(new GridLayout(5, 2));

        bookingPanel.add(new JLabel("Customer Name:"));
        bookingPanel.add(nameField);
        bookingPanel.add(new JLabel("Booking Date:"));
        bookingPanel.add(dateField);
        bookingPanel.add(new JLabel("Room Category:"));
        bookingPanel.add(categoryBox);

        JButton bookBtn = new JButton("Book Room");
        JButton cancelBtn = new JButton("Cancel Booking");
        JButton viewBtn = new JButton("View Bookings");

        bookingPanel.add(bookBtn);
        bookingPanel.add(cancelBtn);
        bookingPanel.add(viewBtn);

        // Output Area
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(bookingPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button Listeners
        bookBtn.addActionListener(e -> bookRoom());
        cancelBtn.addActionListener(e -> cancelBooking());
        viewBtn.addActionListener(e -> viewBookings());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void bookRoom() {
        String name = nameField.getText().trim();
        String date = dateField.getText().trim();
        String category = (String) categoryBox.getSelectedItem();

        if (name.isEmpty() || date.isEmpty()) {
            showMessage("Please enter name and date.");
            return;
        }

        boolean success = manager.bookRoom(name, date, category);
        if (success) {
            showMessage("Booking successful!");
        } else {
            showMessage("No available rooms in category: " + category);
        }
    }

    private void cancelBooking() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            showMessage("Enter customer name to cancel booking.");
            return;
        }

        boolean success = manager.cancelBooking(name);
        if (success) {
            showMessage("Booking canceled.");
        } else {
            showMessage("No booking found for: " + name);
        }
    }

    private void viewBookings() {
        StringBuilder sb = new StringBuilder();
        for (Booking b : manager.getBookings()) {
            sb.append(b).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
        viewBookings();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HotelSystemGUI());
    }
}
