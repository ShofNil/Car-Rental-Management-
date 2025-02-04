package DashBoard;

import Management.Vehicle;
import Management.VehicleManager;
import User.UserStaff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewAvailableVehicles {

    private JFrame frame;
    private JTable vehicleTable;
    private DefaultTableModel tableModel;
    private UserStaff user; // Store the user

    public ViewAvailableVehicles(UserStaff user) {
        this.user = user; // Initialize the user

        frame = new JFrame("Available Vehicles");
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for better layout management
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"Type", "Model", "Image", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        vehicleTable = new JTable(tableModel);
        vehicleTable.setRowHeight(100);
        vehicleTable.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer()); // Use ImageRenderer for image column

        JScrollPane scrollPane = new JScrollPane(vehicleTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        loadAvailableVehicles();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the view
            new StaffDash(user); // Open the StaffDash view with the user
        });
        frame.add(backButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void loadAvailableVehicles() {
        VehicleManager vehicleManager = new VehicleManager();
        try {
            for (Vehicle vehicle : vehicleManager.getAvailableVehicles()) {
                tableModel.addRow(new Object[]{
                        vehicle.getType(), // Type
                        vehicle.getModel(), // Model
                        new ImageIcon(vehicle.getImagePath()), // Image
                        vehicle.getStatus()  // Status
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading available vehicles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}