package com.flight.management.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FlightManagementFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private Timer colorChangeTimer;
    private JTable flightDisplayTable;
    private DefaultTableModel tableModel;

    private Connection connection;

    public FlightManagementFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setTitle("Flight Management");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Flight");
        tableModel.addColumn("Destination");
        tableModel.addColumn("Departure Time");

        flightDisplayTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightDisplayTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton btnDisplayFlights = new JButton("Display Flights");
        btnDisplayFlights.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayFlightsFromDatabase();
            }
        });
        btnDisplayFlights.setFont(new Font("Tahoma", Font.PLAIN, 15));
        buttonPanel.add(btnDisplayFlights);

        JButton btnAddFlight = new JButton("Add Flight");
        btnAddFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFlightToDatabase();
            }
        });
        btnAddFlight.setFont(new Font("Tahoma", Font.PLAIN, 15));
        buttonPanel.add(btnAddFlight);

        JButton btnModifyFlight = new JButton("Modify Flight");
        btnModifyFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifyFlightInDatabase();
            }
        });
        btnModifyFlight.setFont(new Font("Tahoma", Font.PLAIN, 15));
        buttonPanel.add(btnModifyFlight);

        JButton btnDeleteFlight = new JButton("Delete Flight");
        btnDeleteFlight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteFlightFromDatabase();
            }
        });
        btnDeleteFlight.setFont(new Font("Tahoma", Font.PLAIN, 15));
        buttonPanel.add(btnDeleteFlight);

        // Initialize the database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration?useSSL=false", "root", "kalpana123");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Randomly Change Window Color
        colorChangeTimer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                contentPane.setBackground(new Color(
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256),
                        (int) (Math.random() * 256)
                ));
            }
        });
        colorChangeTimer.start();
    }

    private void displayFlightsFromDatabase() {
        // Clear the table before displaying new data
        tableModel.setRowCount(0);

        try {
            String query = "SELECT flight_name, destination, departure_time FROM flights";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String flightName = resultSet.getString("flight_name");
                String destination = resultSet.getString("destination");
                String departureTime = resultSet.getString("departure_time");

                Object[] rowData = {flightName, destination, departureTime};
                tableModel.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Error retrieving flight details from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addFlightToDatabase() {
        String flightName = JOptionPane.showInputDialog(contentPane, "Enter Flight Name:");
        String destination = JOptionPane.showInputDialog(contentPane, "Enter Destination:");
        String departureTime = JOptionPane.showInputDialog(contentPane, "Enter Departure Time:");

        // Validate input data
        if (flightName == null || flightName.trim().isEmpty() ||
            destination == null || destination.trim().isEmpty() ||
            departureTime == null || departureTime.trim().isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Please enter valid data for the flight.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the flight with the same name already exists
        if (isFlightExists(flightName)) {
            JOptionPane.showMessageDialog(contentPane, "Flight with the same name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String query = "INSERT INTO flights (flight_name, destination, departure_time) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, flightName);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, departureTime);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(contentPane, "Flight added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Refresh the display after adding a new flight
                displayFlightsFromDatabase();
            } else {
                JOptionPane.showMessageDialog(contentPane, "Failed to add flight.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Error adding flight to the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isFlightExists(String flightName) {
        try {
            String query = "SELECT flight_name FROM flights WHERE flight_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, flightName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void modifyFlightInDatabase() {
        int selectedRow = flightDisplayTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(contentPane, "Please select a flight to modify.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String flightName = (String) flightDisplayTable.getValueAt(selectedRow, 0);
        String newDestination = JOptionPane.showInputDialog(contentPane, "Enter New Destination:");
        String newDepartureTime = JOptionPane.showInputDialog(contentPane, "Enter New Departure Time:");

        try {
            String query = "UPDATE flights SET destination=?, departure_time=? WHERE flight_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newDestination);
            preparedStatement.setString(2, newDepartureTime);
            preparedStatement.setString(3, flightName);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(contentPane, "Flight modified successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Refresh the display after modifying a flight
                displayFlightsFromDatabase();
            } else {
                JOptionPane.showMessageDialog(contentPane, "Failed to modify flight.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Error modifying flight in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteFlightFromDatabase() {
        int selectedRow = flightDisplayTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(contentPane, "Please select a flight to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String flightName = (String) flightDisplayTable.getValueAt(selectedRow, 0);

        try {
            if (flightName != null && !flightName.isEmpty()) {
                String query = "DELETE FROM flights WHERE flight_name=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, flightName);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(contentPane, "Flight deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Refresh the display after deleting a flight
                    displayFlightsFromDatabase();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Failed to delete flight.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(contentPane, "Selected row has no flight name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(contentPane, "Error deleting flight from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FlightManagementFrame frame = new FlightManagementFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
