import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int scheduledDepartureTime;
    private int actualDepartureTime;
    private int delayMinutes;

    public Flight(String flightNumber, String origin, String destination,
                  int scheduledDepartureTime, int actualDepartureTime, int delayMinutes) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.actualDepartureTime = actualDepartureTime;
        this.delayMinutes = delayMinutes;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public int getActualDepartureTime() {
        return actualDepartureTime;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(int delayMinutes) {
        this.delayMinutes = delayMinutes;
    }
}

public class FlightDelaySwingApp extends JFrame {
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton predictButton;
    private JTable flightTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane; // Declare scrollPane as a class member

    private Map<String, Flight> flightsMap;

    public FlightDelaySwingApp() {
        flightsMap = new HashMap<>();

        setTitle("Flight Delay Prediction");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        addComponents();
        addListeners();
    }

    private void initComponents() {
        addButton = new JButton("Add");
        modifyButton = new JButton("Modify");
        deleteButton = new JButton("Delete");
        predictButton = new JButton("Predict");

        tableModel = new DefaultTableModel();
        flightTable = new JTable(tableModel);
        scrollPane = new JScrollPane(flightTable); // Initialize scrollPane

        setLayout(new BorderLayout());

        tableModel.addColumn("Flight Number");
        tableModel.addColumn("Origin");
        tableModel.addColumn("Destination");
        tableModel.addColumn("Scheduled Departure Time");
        tableModel.addColumn("Actual Departure Time");
        tableModel.addColumn("Delay Minutes");
    }

    private void addComponents() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(predictButton);

        add(scrollPane, BorderLayout.CENTER); // Use the class member scrollPane
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFlightDialog();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyFlight();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFlight();
            }
        });

        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                predictDelay();
            }
        });
    }

    private void addFlightDialog() {
        JTextField flightNumberField = new JTextField();
        JTextField originField = new JTextField();
        JTextField destinationField = new JTextField();
        JTextField scheduledDepartureField = new JTextField();
        JTextField actualDepartureField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Flight Number:"));
        panel.add(flightNumberField);
        panel.add(new JLabel("Origin:"));
        panel.add(originField);
        panel.add(new JLabel("Destination:"));
        panel.add(destinationField);
        panel.add(new JLabel("Scheduled Departure Time:"));
        panel.add(scheduledDepartureField);
        panel.add(new JLabel("Actual Departure Time:"));
        panel.add(actualDepartureField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Flight", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String flightNumber = flightNumberField.getText();
            String origin = originField.getText();
            String destination = destinationField.getText();
            int scheduledDeparture = Integer.parseInt(scheduledDepartureField.getText());
            int actualDeparture = Integer.parseInt(actualDepartureField.getText());

            Flight flight = new Flight(flightNumber, origin, destination, scheduledDeparture, actualDeparture, 0);
            flightsMap.put(flightNumber, flight);

            Object[] rowData = {flightNumber, origin, destination, scheduledDeparture, actualDeparture, 0};
            tableModel.addRow(rowData);
        }
    }

    private void modifyFlight() {
        int selectedRow = flightTable.getSelectedRow();
        if (selectedRow != -1) {
            JTextField flightNumberField = new JTextField(tableModel.getValueAt(selectedRow, 0).toString());
            JTextField originField = new JTextField(tableModel.getValueAt(selectedRow, 1).toString());
            JTextField destinationField = new JTextField(tableModel.getValueAt(selectedRow, 2).toString());
            JTextField scheduledDepartureField = new JTextField(tableModel.getValueAt(selectedRow, 3).toString());
            JTextField actualDepartureField = new JTextField(tableModel.getValueAt(selectedRow, 4).toString());

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("Flight Number:"));
            panel.add(flightNumberField);
            panel.add(new JLabel("Origin:"));
            panel.add(originField);
            panel.add(new JLabel("Destination:"));
            panel.add(destinationField);
            panel.add(new JLabel("Scheduled Departure Time:"));
            panel.add(scheduledDepartureField);
            panel.add(new JLabel("Actual Departure Time:"));
            panel.add(actualDepartureField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Modify Flight", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String flightNumber = flightNumberField.getText();
                String origin = originField.getText();
                String destination = destinationField.getText();
                int scheduledDeparture = Integer.parseInt(scheduledDepartureField.getText());
                int actualDeparture = Integer.parseInt(actualDepartureField.getText());

                Flight flight = new Flight(flightNumber, origin, destination, scheduledDeparture, actualDeparture, 0);
                flightsMap.put(flightNumber, flight);

                tableModel.setValueAt(flightNumber, selectedRow, 0);
                tableModel.setValueAt(origin, selectedRow, 1);
                tableModel.setValueAt(destination, selectedRow, 2);
                tableModel.setValueAt(scheduledDeparture, selectedRow, 3);
                tableModel.setValueAt(actualDeparture, selectedRow, 4);
                tableModel.setValueAt(0, selectedRow, 5);
            }
        }
    }

    private void deleteFlight() {
        int selectedRow = flightTable.getSelectedRow();
        if (selectedRow != -1) {
            String flightNumber = (String) tableModel.getValueAt(selectedRow, 0);
            flightsMap.remove(flightNumber);
            tableModel.removeRow(selectedRow);
        }
    }

    private void predictDelay() {
        int selectedRow = flightTable.getSelectedRow();
        if (selectedRow != -1) {
            JTextField flightNumberField = new JTextField(tableModel.getValueAt(selectedRow, 0).toString());

            JPanel panel = new JPanel(new GridLayout(1, 2));
            panel.add(new JLabel("Flight Number:"));
            panel.add(flightNumberField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Predict Delay", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String flightNumber = flightNumberField.getText();
                Flight flight = flightsMap.get(flightNumber);

                // Perform prediction logic here (for simplicity, setting delay to 15 minutes)
                int predictedDelay = 15;

                flight.setDelayMinutes(predictedDelay);
                tableModel.setValueAt(predictedDelay, selectedRow, 5);

                JOptionPane.showMessageDialog(null, "Prediction for Flight " + flightNumber + ": Delay of " + predictedDelay + " minutes");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlightDelaySwingApp().setVisible(true);
            }
        });
    }
}
