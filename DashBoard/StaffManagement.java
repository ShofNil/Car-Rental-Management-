package DashBoard;

import User.UserStaff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Login.staff;


import java.awt.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffManagement {
    private static final String STAFF_FILE_PATH = "staff_data.txt"; // Path to the staff data file
    private List<UserStaff> staffList;

    public StaffManagement() {
        staffList = new ArrayList<>();
        loadStaff(); // Load staff from the file when the manager is created
        showStaffManagement(); // Show the staff management interface
    }

    // Load staff from the file
    private void loadStaff() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] staffData = line.split(",");
                if (staffData.length >= 3) {
                    staffList.add(new UserStaff(staffData[0], staffData[1], staffData[2])); // Assuming UserStaff has a constructor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show staff management interface
    private void showStaffManagement() {
        JFrame frame = new JFrame("Staff Management");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"Staff Name", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        for (UserStaff Staff: staffList) {
            tableModel.addRow(new Object[]{staff.getName(), staff.getEmail()});
        }

        JButton removeButton = new JButton("Remove Staff");
        removeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String staffName = (String) tableModel.getValueAt(selectedRow, 0);
                removeStaff(staffName);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a staff member to remove.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Remove a staff member
    public void removeStaff(String staffName) {
        staffList.removeIf(staff -> staff.getName().equals(staffName));
        saveStaff(); // Save updated staff to the file
    }

    // Save staff back to the file
    private void saveStaff() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STAFF_FILE_PATH))) {
            for (UserStaff Staff  : staffList) {
                writer.write(staff.getName() + "," + staff.getEmail() + "," + staff.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}