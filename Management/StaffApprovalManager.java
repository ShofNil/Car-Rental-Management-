package Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffApprovalManager {
    private static final String STAFF_APPROVAL_FILE_PATH = "staff_approval_requests.txt"; // Path to the staff approval requests file
    private List<StaffApprovalRequest> requests;

    public StaffApprovalManager() {
        requests = new ArrayList<>();
        loadApprovalRequests(); // Load requests from the file when the manager is created
        showApprovalRequests(); // Show the approval requests in a dialog
    }

    // Load approval requests from the file
    private void loadApprovalRequests() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_APPROVAL_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] requestData = line.split(",");
                if (requestData.length >= 3) {
                    requests.add(new StaffApprovalRequest(requestData[0], requestData[1], requestData[2])); // Assuming StaffApprovalRequest has a constructor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show approval requests in a dialog
    private void showApprovalRequests() {
        JFrame frame = new JFrame("Staff Approval Requests");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"Staff Name", "Email", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        for (StaffApprovalRequest request : requests) {
            tableModel.addRow(new Object[]{request.getStaffName(), request.getEmail(), request.getStatus()});
        }

        JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String staffName = (String) tableModel.getValueAt(selectedRow, 0);
                approveRequest(staffName);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a request to approve.");
            }
        });

        JButton rejectButton = new JButton("Reject");
        rejectButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String staffName = (String) tableModel.getValueAt(selectedRow, 0);
                rejectRequest(staffName);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a request to reject.");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Approve a staff request
    public void approveRequest(String staffName) {
        for (StaffApprovalRequest request : requests) {
            if (request.getStaffName().equals(staffName)) {
                request.setStatus("Approved");
                saveApprovalRequests(); // Save updated requests to the file
                return;
            }
        }
    }

    // Reject a staff request
    public void rejectRequest(String staffName) {
        requests.removeIf(request -> request.getStaffName().equals(staffName));
        saveApprovalRequests(); // Save updated requests to the file
    }

    // Save approval requests back to the file
    private void saveApprovalRequests() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STAFF_APPROVAL_FILE_PATH))) {
            for (StaffApprovalRequest request : requests) {
                writer.write(request.getStaffName() + "," + request.getEmail() + "," + request.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all approval requests
    public List<StaffApprovalRequest> getAllRequests() {
        return requests;
    }
}

// Class to represent a staff approval request
class StaffApprovalRequest {
    private String staffName;
    private String email;
    private String status; // Approved or Pending

    public StaffApprovalRequest(String staffName, String email, String requestData) {
        this.staffName = staffName;
        this.email = email;
        this.status = "Pending"; // Default status
    }

    public String getStaffName() {
        return staffName;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}