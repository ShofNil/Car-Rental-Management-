package DashBoard;

import javax.swing.*;
import User.UserCustomer;
import java.awt.*;
import java.awt.event.*;

public class CustomDash implements ActionListener {
    private JFrame frame;
    private UserCustomer user;

    public CustomDash(UserCustomer user) {
        this.user = user;

        frame = new JFrame("Customer Dashboard " + user.getName());
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Welcome " + user.getName());
        titleLabel.setBounds(520, 200, 300, 30);
        frame.add(titleLabel);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setOpaque(false);

        JButton verifyButton = new JButton("Verify Yourself");
        verifyButton.setBounds(500, 340, 200, 30);
        frame.add(verifyButton);
        verifyButton.addActionListener(this);
        verifyButton.setForeground(Color.BLACK);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Verify Yourself")) {
                //openVerificationWindow();
                new CustomerDashboard(user).setVisible(true);
                frame.dispose();
            }
        }
    }

   /* private void openVerificationWindow() {
        JFrame verifyFrame = new JFrame("Verify Yourself");
        verifyFrame.setSize(400, 400);
        verifyFrame.setLayout(null);
        verifyFrame.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        verifyFrame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);
        verifyFrame.add(nameField);

        JLabel nidLabel = new JLabel("NID:");
        nidLabel.setBounds(50, 100, 100, 30);
        verifyFrame.add(nidLabel);

        JTextField nidField = new JTextField();
        nidField.setBounds(150, 100, 200, 30);
        verifyFrame.add(nidField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 150, 100, 30);
        verifyFrame.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 150, 200, 30);
        verifyFrame.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 200, 100, 30);
        verifyFrame.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(150, 200, 200, 30);
        verifyFrame.add(addressField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 250, 100, 30);
        verifyFrame.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String nid = nidField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();

            if (name.isEmpty() || nid.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(verifyFrame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                verifyFrame.dispose();
                frame.dispose();
                new CustomerDashboard(user).setVisible(true);
            }
        });*/

       // verifyFrame.setVisible(true);
    }
