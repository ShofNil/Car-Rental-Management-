package Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerVerificationManager {
    private static final String VERIFICATION_FILE_PATH = "customer_verification_requests.txt"; // Path to the verification requests file
    private List<CustomerVerificationRequest> requests;

    public CustomerVerificationManager() {
        requests = new ArrayList<>();
        loadVerificationRequests(); // Load requests from the file when the manager is created
        showVerificationRequests(); // Show the verification requests in a dialog
    }

    // Load verification requests from the file
    private void loadVerificationRequests() {
        try (BufferedReader reader = new BufferedReader(new FileReader(VERIFICATION_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] requestData = line.split(",");
                if (requestData.length >= 3) {
                    requests.add(new CustomerVerificationRequest(requestData[0], requestData[1], requestData[2])); // Assuming CustomerVerificationRequest has a constructor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show verification requests in a dialog
    private void showVerificationRequests() {
        JFrame frame = new JFrame("Customer Verification Requests");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"Customer Name", "NID", "Phone", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        for (CustomerVerificationRequest request : requests) {
            tableModel.addRow(new Object[]{request.getCustomerName(), request.getNid(), request.getPhone(), request.getStatus()});
        }

        JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {
                String customerName = (String) tableModel.getValueAt(selectedRow, 0);
                approveRequest(customerName);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a request to approve.");
            }
        });

        JButton rejectButton = new JButton("Reject");
        rejectButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String customerName = (String) tableModel.getValueAt(selectedRow, 0);
                rejectRequest(customerName);
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

    // Approve a verification request
    public void approveRequest(String customerName) {
        for (CustomerVerificationRequest request : requests) {
            if (request.getCustomerName().equals(customerName)) {
                request.setStatus("Approved");
                saveVerificationRequests(); // Save updated requests to the file
                return;
            }
        }
    }

    // Reject a verification request
    public void rejectRequest(String customerName) {
        requests.removeIf(request -> request.getCustomerName().equals(customerName));
        saveVerificationRequests(); // Save updated requests to the file
    }

    // Save verification requests back to the file
    private void saveVerificationRequests() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VERIFICATION_FILE_PATH))) {
            for (CustomerVerificationRequest request : requests) {
                writer.write(request.getCustomerName() + "," + request.getNid() + "," + request.getPhone() + "," + request.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}