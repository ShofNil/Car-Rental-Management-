package DashBoard;

import javax.swing.*;
import Login.llogin;
import User.UserStaff; // Assuming you have a UserStaff class similar to UserAdmin
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StaffDash implements ActionListener {
    private UserStaff user = null;
    private JFrame frame;

    public StaffDash(UserStaff user) {
        this.user = user;

        frame = new JFrame("Staff Dashboard - " + user.getName());
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Welcome " + user.getName());
        titleLabel.setBounds(520, 200, 300, 30);
        frame.add(titleLabel);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);

        JButton viewVehiclesButton = new JButton("View Available Vehicles");
        viewVehiclesButton.setBounds(500, 260, 200, 30);
        frame.add(viewVehiclesButton);
        viewVehiclesButton.addActionListener(this);
        viewVehiclesButton.setForeground(Color.BLACK);

        JButton manageRentalsButton = new JButton("Manage Rentals");
        manageRentalsButton.setBounds(500, 300, 200, 30);
        frame.add(manageRentalsButton);
        manageRentalsButton.addActionListener(this);
        manageRentalsButton.setForeground(Color.BLACK);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(500, 380, 200, 30);
        frame.add(logoutButton);
        logoutButton.addActionListener(this);
        logoutButton.setForeground(Color.BLACK);

        JButton addVehiclesForRent = new JButton("Add Vehicles for Rent");
        addVehiclesForRent.setBounds(500, 340, 200, 30);
        frame.add(addVehiclesForRent);
        addVehiclesForRent.addActionListener(this);
        addVehiclesForRent.setForeground(Color.BLACK);

        // New Report Manager button
        /*
        JButton reportManagerButton = new JButton("Report Manager");
        reportManagerButton.setBounds(500, 380, 200, 30);
        frame.add(reportManagerButton);
        reportManagerButton.addActionListener(this);
        reportManagerButton.setForeground(Color.BLACK);

         */

        // Background image
        ImageIcon background = new ImageIcon("bgdash.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, 1280, 720);
        frame.add(backgroundLabel);

        // Set icon
        ImageIcon icon = new ImageIcon("icon.png");  // icon
        frame.setIconImage(icon.getImage());

        frame.setVisible(true);
    }

    public StaffDash() {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("View Available Vehicles")) {
                new ViewAvailableVehicles(user); // Navigate to view available vehicles
                frame.dispose();
            } else if (button.getText().equals("Add Vehicles for Rent")) {
                new Add(user); // Pass the user to the Add class
                frame.dispose();
            } else if (button.getText().equals("Manage Rentals")) {
                new ManageRentals(user); // Navigate to manage rentals
                // frame.dispose();
            }  else if (button.getText().equals("Logout")) {
                JOptionPane.showMessageDialog(null, "Logging out!");
                frame.dispose();
                new llogin(); // Navigate to login screen
            }
        }
    }
}

// Assuming you have a ReportManager class
/*
class ReportManager {
    private UserStaff user;
    private JFrame frame;
    private JTextArea reportArea;
    private ArrayList<String> reports; // To store generated reports

    public ReportManager(UserStaff user) {
        this.user = user;
        reports = new ArrayList<>(); // Initialize the reports list

        frame = new JFrame("Report Manager - " + user.getName());
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Report Manager");
        titleLabel.setBounds(550, 20, 200, 30);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        frame.add(titleLabel);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setBounds(60, 70, 200, 30);
        frame.add(generateReportButton);
        generateReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        JButton viewReportsButton = new JButton("View Reports");
        viewReportsButton.setBounds(530, 70, 200, 30);
        frame.add(viewReportsButton);
        viewReportsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewReports();
            }
        });

        JButton exportReportButton = new JButton("Export Report");
        exportReportButton.setBounds(1000, 70, 200, 30);
        frame.add(exportReportButton);
        exportReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportReport();
            }
        });
        JButton backButton = new JButton("Back");
        backButton.setBounds(40, 530, 1220, 30);
        backButton.setBackground(Color.PINK); // Set background color
        backButton.setForeground(Color.RED);
        frame.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the ReportManager frame
            }
        });


        reportArea = new JTextArea();
        reportArea.setBounds(50, 120, 1200, 400);
        reportArea.setEditable(false);
        frame.add(reportArea);

        frame.setVisible(true);
    }


    private void generateReport() {
        // Mock report generation
        String report = "Report generated on " + java.time.LocalDate.now() + "\n";
        report += ":User  " + user.getName() + "\n";
        report += "Report Content: Sample data...\n\n";
        reports.add(report);
        JOptionPane.showMessageDialog(frame, "Report generated successfully!");
    }

    private void viewReports() {
        // Display all generated reports
        StringBuilder allReports = new StringBuilder();
        for (String report : reports) {
            allReports.append(report).append("\n");
        }
        reportArea.setText(allReports.toString());
    }

    private void exportReport() {
        // Mock export functionality
        if (reports.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No reports to export!");
            return;
        }
        // Here you would implement actual export logic (e.g., saving to a file)
        JOptionPane.showMessageDialog(frame, "Reports exported successfully!");
    }
}

 */