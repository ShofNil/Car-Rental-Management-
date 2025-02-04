package DashBoard;

import javax.swing.*;

import Login.llogin;
import Management.CustomerVerificationManager;
import User.UserAdmin;

import java.awt.*;
import java.awt.event.*;

public class AdminDash implements ActionListener {
    private JFrame frame;

    public AdminDash() {
        frame = new JFrame("Admin Dashboard");
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setBounds(520, 50, 300, 30);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        frame.add(titleLabel);

        JButton verifyCustomerButton = new JButton("Verify Customer");
        verifyCustomerButton.setBounds(500, 150, 200, 30);
        verifyCustomerButton.addActionListener(this);
        frame.add(verifyCustomerButton);

        JButton staffApprovalButton = new JButton("Staff Approval");
        staffApprovalButton.setBounds(500, 200, 200, 30);
        staffApprovalButton.addActionListener(this);
        frame.add(staffApprovalButton);

        JButton staffManagementButton = new JButton("Staff Management");
        staffManagementButton.setBounds(500, 250, 200, 30);
        staffManagementButton.addActionListener(this);
        frame.add(staffManagementButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 300, 200, 30);
        logoutButton.addActionListener(this);
        frame.add(logoutButton);

        frame.setVisible(true);


    }

    public AdminDash(UserAdmin user) {
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Verify Customer")) {
                new CustomerVerificationManager(); // Open customer verification window
            } else if (button.getText().equals("Staff Approval")) {
                new StaffApproval(); // Open staff approval window
            } else if (button.getText().equals("Staff Management")) {
                new StaffManagement(); // Open staff management window
            } else if (button.getText().equals("Logout")) {
                JOptionPane.showMessageDialog(null, "Logging out!");
                frame.dispose();
                new llogin(); // Navigate to login screen
            }
        }
    }

public void CustomerVerification() {
    JFrame frame = new JFrame("Customer Verification");
    frame.setSize(800, 600);
    frame.setLayout(new BorderLayout());

    // Sample data for demonstration
    String[] columnNames = {"Customer Name", "Verification Status"};
    String[][] data = {
        {"John Doe", "Pending"},
        {"Jane Smith", "Pending"}
    };

    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    frame.add(scrollPane, BorderLayout.CENTER);

    JButton approveButton = new JButton("Approve");
    approveButton.addActionListener(e -> {
        // Logic to approve selected customer
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            table.setValueAt("Approved", selectedRow, 1);
            JOptionPane.showMessageDialog(frame, "Customer approved!");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a customer to approve.");
        }
    });

    JButton rejectButton = new JButton("Reject");
    rejectButton.addActionListener(e -> {
        // Logic to reject selected customer
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            table.setValueAt("Rejected", selectedRow, 1);
            JOptionPane.showMessageDialog(frame, "Customer rejected!");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a customer to reject.");
        }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(approveButton);
    buttonPanel.add(rejectButton);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    frame.setVisible(true);
}

public void StaffApproval() {
    JFrame frame = new JFrame("Staff Approval");
    frame.setSize(800, 600);
    frame.setLayout(new BorderLayout());

    // Sample data for demonstration
    String[] columnNames = {"Staff Name", "Approval Status"};
    String[][] data = {
        {"Alice Johnson", "Pending"},
        {"Bob Brown", "Pending"}
    };

    JTable table = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(table);
    frame.add(scrollPane, BorderLayout.CENTER);

    JButton approveButton = new JButton("Approve");
    approveButton.addActionListener(e -> {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            table.setValueAt("Approved", selectedRow, 1);
            JOptionPane.showMessageDialog(frame, "Staff approved!");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a staff member to approve.");
        }
    });

    JButton rejectButton = new JButton("Reject");
    rejectButton.addActionListener(e -> {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            table.setValueAt("Rejected", selectedRow, 1);
            JOptionPane.showMessageDialog(frame, "Staff rejected!");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a staff member to reject.");
        }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(approveButton);
    buttonPanel.add(rejectButton);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    frame.setVisible(true);
}}

    