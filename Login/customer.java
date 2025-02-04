package Login;

import javax.swing.*;

import Management.CustomerVerificationRequest;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import User.*;

public class customer implements ActionListener {

    private JFrame frame;
    private JTextField nameField, emailField, nidField, phoneField, addressField;
    private JPasswordField passwordField;

    public customer() {
        frame = new JFrame("Customer Registration");
        frame.setSize(1280, 720);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 150, 100, 30);
        frame.add(nameLabel);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        nameField = new JTextField();
        nameField.setBounds(150, 150, 200, 30);
        frame.add(nameField);

        JLabel customerLabel = new JLabel("CUSTOMER REGISTRATION");
        customerLabel.setBounds(100, 70, 350, 30);
        frame.add(customerLabel);
        customerLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        customerLabel.setForeground(Color.BLACK);
        customerLabel.setOpaque(false);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 200, 100, 30);
        frame.add(emailLabel);
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));

        emailField = new JTextField();
        emailField.setBounds(150, 200, 200, 30);
        frame.add(emailField);

        JLabel nidLabel = new JLabel("NID:");
        nidLabel.setBounds(50, 250, 100, 30);
        frame.add(nidLabel);
        nidLabel.setForeground(Color.BLACK);
        nidLabel.setFont(new Font("Arial", Font.BOLD, 16));

        nidField = new JTextField();
        nidField.setBounds(150, 250, 200, 30);
        frame.add(nidField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 300, 100, 30);
        frame.add(phoneLabel);
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));

        phoneField = new JTextField();
        phoneField.setBounds(150, 300, 200, 30);
        frame.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 350, 100, 30);
        frame.add(addressLabel);
        addressLabel.setForeground(Color.BLACK);
        addressLabel.setFont(new Font("Arial", Font.BOLD, 16));

        addressField = new JTextField();
        addressField.setBounds(150, 350, 200, 30);
        frame.add(addressField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 400, 100, 30);
        frame.add(passwordLabel);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 400, 200, 30);
        frame.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 450, 100, 30);
        registerButton.addActionListener(this);
        frame.add(registerButton);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setForeground(Color.BLACK);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(260, 450, 100, 30);
        clearButton.addActionListener(this);
        frame.add(clearButton);
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setForeground(Color.BLACK);

        JLabel loginLabel = new JLabel("Already Registered? Login");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setBounds(150, 500, 200, 30);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new llogin();
            }
        });



        ImageIcon background = new ImageIcon("5.gif");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(250,-120, 1280, 720);
        frame.add(backgroundLabel);

        ImageIcon background1 = new ImageIcon("bg.png");
        JLabel backgroundLabel1 = new JLabel(background1);
        backgroundLabel1.setBounds(0,0, 1280, 720);
        frame.add(backgroundLabel1);


        ImageIcon icon = new ImageIcon("icon.png");
        frame.setIconImage(icon.getImage());

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.add(loginLabel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Register")) {
                register();
            } else if (button.getText().equals("Clear")) {
                clearFields();
            }
        }
    }

    private void register() {
        String name = nameField.getText();
        String email = emailField.getText();
        String nid = nidField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String password = new String(passwordField.getPassword());

        // Check for empty fields
        if (name.isEmpty() || email.isEmpty() || nid.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!");
            return;
        }

        // Check for existing email
        if (ifEmailExists(email)) {
            JOptionPane.showMessageDialog(frame, "User  with this email already exists!");
            return;
        }

        UserCustomer newUser  = new UserCustomer(name, email, password);
        
        // Save the verification request
        saveVerificationRequest(new CustomerVerificationRequest(name, nid, phone));
        
        // Continue with the rest of the registration logic...
        try {
            FileWriter writer = new FileWriter("userCustomerdata.txt", true);
            writer.write(newUser .getName() + "," + newUser .getEmail() + "," + newUser .getPassword() + "\n");
            writer.close();

            // Create the empty file for the new user
            String filePath = newUser .getName() + "_rented_vehicles.txt";
            File rentedVehiclesFile = new File(filePath);
            rentedVehiclesFile.createNewFile(); // Creates the file without writing anything

            JOptionPane.showMessageDialog(frame, "Registration Successful!");
            frame.dispose();
            new llogin();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error during registration: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void saveVerificationRequest(CustomerVerificationRequest request) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customer_verification_requests.txt", true))) {
            writer.write(request.getCustomerName() + "," + request.getNid() + "," + request.getPhone() + ",Pending");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean ifEmailExists(String email) {
        try {
            File file = new File("userCustomerdata.txt");
            if (!file.exists()) {
                return false;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(email)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        nidField.setText("");
        phoneField.setText("");
        addressField.setText("");
        passwordField.setText("");
    }
}