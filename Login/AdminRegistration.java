package Login;

import javax.swing.*;

import User.UserAdmin;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AdminRegistration implements ActionListener {
    private JFrame frame;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;

    public AdminRegistration() {
        frame = new JFrame("Admin Registration");
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

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 200, 100, 30);
        frame.add(emailLabel);
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));

        emailField = new JTextField();
        emailField.setBounds(150, 200, 200, 30);
        frame.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 250, 100, 30);
        frame.add(passwordLabel);
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 250, 200, 30);
        frame.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 300, 100, 30);
        registerButton.addActionListener(this);
        frame.add(registerButton);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setForeground(Color.BLACK);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(260, 300, 100, 30);
        clearButton.addActionListener(this);
        frame.add(clearButton);
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.setForeground(Color.BLACK);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
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
        String password = new String(passwordField.getPassword());

        // Check for empty fields
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!");
            return;
        }

        // Check for existing email
        if (ifEmailExists(email)) {
            JOptionPane.showMessageDialog(frame, "User  with this email already exists!");
            return;
        }

        UserAdmin newUser  = new UserAdmin(name, email, password);

        try {
            FileWriter writer = new FileWriter("userAdmindata.txt", true);
            writer.write(newUser .getName() + "," + newUser .getEmail() + "," + newUser .getPassword() + "\n");
            writer.close();

            JOptionPane.showMessageDialog(frame, "Registration Successful!");
            frame.dispose();
            new LogAdmin(); // Navigate to admin login after registration
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean ifEmailExists(String email) {
        try {
            File file = new File("userAdmindata.txt");
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
        passwordField.setText("");
    }
}