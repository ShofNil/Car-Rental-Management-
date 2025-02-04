package DashBoard;

import Management.RentalManager;
import User.UserStaff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageRentals implements ActionListener {

    private JFrame frame;
    private JTable rentalTable;
    private DefaultTableModel tableModel;
    private JButton backButton;
    private JButton returnVehicleButton; // Button to return a vehicle
    private JButton addRentalButton; // Button to add a new rental
    private RentalManager rentalManager; // Moved RentalManager to class level

    public ManageRentals(UserStaff user) {
        frame = new JFrame("Manage Rentals");
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for better layout management
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"Customer Name", "Vehicle Type", "Vehicle Model", "Rental Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        rentalTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(rentalTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        rentalManager = new RentalManager(); // Initialize RentalManager
        loadRentals();

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        returnVehicleButton = new JButton("Return Vehicle");
        returnVehicleButton.addActionListener(this);
        buttonPanel.add(returnVehicleButton);

        addRentalButton = new JButton("Add Rental");
        addRentalButton.addActionListener(this);
        buttonPanel.add(addRentalButton); // Add the new button to the panel

        frame.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the south

        frame.setVisible(true);
    }

    private void loadRentals() {
        for (String[] rental : rentalManager.getAllRentals()) {
            tableModel.addRow(rental);
        }
    }

    private void showAddRentalDialog() {
        // Create a dialog for adding a new rental
        JDialog dialog = new JDialog(frame, "Add Rental", true);
        dialog.setLayout(new GridLayout(6, 2));
        dialog.setSize(400, 300); // Adjusted size for better usability
        dialog.setLocationRelativeTo(null);

        JTextField customerNameField = new JTextField();
        JTextField vehicleTypeField = new JTextField();
        JTextField vehicleModelField = new JTextField();
        JTextField rentalStatusField = new JTextField();

        dialog.add(new JLabel("Customer Name:"));
        dialog.add(customerNameField);
        dialog.add(new JLabel("Vehicle Type:"));
        dialog.add(vehicleTypeField);
        dialog.add(new JLabel("Vehicle Model:"));
        dialog.add(vehicleModelField);
        dialog.add(new JLabel("Rental Status:"));
        dialog.add(rentalStatusField);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            String customerName = customerNameField.getText().trim();
            String vehicleType = vehicleTypeField.getText().trim();
            String vehicleModel = vehicleModelField.getText().trim();
            String rentalStatus = rentalStatusField.getText().trim();

            // Validate input fields
            if (customerName.isEmpty() || vehicleType.isEmpty() || vehicleModel.isEmpty() || rentalStatus.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Logic to add the rental
            rentalManager.addRental(customerName, vehicleType, vehicleModel, rentalStatus); // Call the add method
            tableModel.addRow(new String[]{customerName, vehicleType, vehicleModel, rentalStatus}); // Add to table

            JOptionPane.showMessageDialog(dialog, "Rental added successfully for " + customerName + "!");
            dialog.dispose(); // Close the dialog
        });

        dialog.add(addButton);
        dialog.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) e.getSource();
            if (sourceButton == backButton) {
                frame.dispose();
                new StaffDash(); // Close the manage rentals view
            } else if (sourceButton == returnVehicleButton) {
                int selectedRow = rentalTable.getSelectedRow();
                if (selectedRow != -1) {
                    String customerName = (String) tableModel.getValueAt(selectedRow, 0);
                    String vehicleType = (String) tableModel.getValueAt(selectedRow, 1);
                    String vehicleModel = (String) tableModel.getValueAt(selectedRow, 2);

                    // Logic to return the vehicle
                    rentalManager.returnVehicle(customerName, vehicleType, vehicleModel); // Call the return method

                    JOptionPane.showMessageDialog(frame, "Vehicle returned successfully for " + customerName + "!");
                    tableModel.removeRow(selectedRow); // Remove the row from the table
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a rental to return.");
                }
            } else if (sourceButton == addRentalButton) {
                showAddRentalDialog(); // Show the dialog to add a new rental
            }
        }
    }
}