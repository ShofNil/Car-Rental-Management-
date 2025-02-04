package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import User.*;
import DashBoard.*;

public class LogAdmin implements ActionListener {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LogAdmin() {
        frame = new JFrame("Admin Login");
        frame.setSize(1280, 7200);
        frame.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(840, 200, 100, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setForeground(Color.BLACK);
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(940, 200, 200, 30);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        frame.add(emailField);
        emailField.setOpaque(false);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(840, 250, 100, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.BLACK);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(940, 250, 200, 30);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        frame.add(passwordField);
        passwordField.setOpaque(false);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(970, 300, 100, 30);
        loginButton.addActionListener(this);
        frame.add(loginButton);
        loginButton.setForeground(Color.BLACK);

       /* JButton registerButton = new JButton("Register");
        registerButton.setBounds(940, 350, 100, 30);
        registerButton.addActionListener(e -> {
            frame.dispose();
            new AdminRegistration(); // Navigate to admin registration
        });
        frame.add(registerButton);*/
        //registerButton.setForeground(Color.BLACK);


        ImageIcon background = new ImageIcon("bgdash.png");

        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0,0, 1280, 720);
        frame.add(backgroundLabel);


        ImageIcon icon = new ImageIcon("icon.png");  // icon
        frame.setIconImage(icon.getImage());



        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Login")) {
                login();
            }
        }
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String e = "Admin@gmail.com";
        String p = "1234";
        UserAdmin user = null;
        boolean found = false;

        if (e.equals(email) && p.equals(password)) {
            found = true;
        }

            if (found) {
                JOptionPane.showMessageDialog(frame, "Login Successful!", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new AdminDash(); // Navigate to admin dashboard
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email or password!", "TRY AGAIN", JOptionPane.ERROR_MESSAGE);
            }

    }
}