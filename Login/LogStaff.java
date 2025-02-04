package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import User.*;
import DashBoard.*;

public class LogStaff implements ActionListener {

    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LogStaff() {
        frame = new JFrame("Welcome!");
        frame.setSize(1280, 600);
        frame.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(840, 200, 100, 30);
        frame.add(emailLabel);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setForeground(Color.BLACK);

        JLabel staffLabel = new JLabel("STAFF LOGIN");
        staffLabel.setBounds(900, 30, 300, 30);
        frame.add(staffLabel);
        staffLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        staffLabel.setForeground(Color.BLACK);
        staffLabel.setOpaque(false);

        emailField = new JTextField();
        emailField.setBounds(940, 200, 200, 30);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        emailField.setOpaque(false);
        frame.add(emailField);

        emailField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String emailText = emailField.getText();
                // Regex to match email format
                String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

                if (!emailText.matches(emailPattern)) {
                    emailField.setBackground(Color.PINK); // Show error by changing background
                } else {
                    emailField.setBackground(Color.GREEN); // Reset background on valid input
                }
            }
        });

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(840, 250, 100, 30);
        frame.add(passwordLabel);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.BLACK);

        passwordField = new JPasswordField();
        passwordField.setBounds(940, 250, 200, 30);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(940, 300, 100, 30);
        loginButton.addActionListener(this);
        frame.add(loginButton);
        loginButton.setForeground(Color.BLACK);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(940, 350, 100, 30);
        registerButton.addActionListener(this);
        frame.add(registerButton);
        registerButton.setForeground(Color.BLACK);

        JButton backButton = new JButton("Go Back");
        backButton.setBounds(940, 400, 100, 30);
        backButton.addActionListener(this);
        frame.add(backButton);
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.BLACK);

        // Background image
        ImageIcon background1 = new ImageIcon("8.png");

        JLabel backgroundLabel1 = new JLabel(background1);
        backgroundLabel1.setBounds(-60, -82, 1280, 720);
        frame.add(backgroundLabel1);

        ImageIcon background2 = new ImageIcon("8.png");

       JLabel backgroundLabel2 = new JLabel(background2);
      backgroundLabel2.setBounds(-60, -32, 1280, 720);
       frame.add(backgroundLabel2);

        ImageIcon background = new ImageIcon("6.gif");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(-350,-17, 1280, 600);
        frame.add(backgroundLabel);

        ImageIcon background3 = new ImageIcon("bg3.png");

        JLabel backgroundLabel3 = new JLabel(background3);
        backgroundLabel3.setBounds(0,0, 1280, 720);
        frame.add(backgroundLabel3);

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
                login(); // Call the login method
            } else if (button.getText().equals("Register")) {
                frame.dispose();
                new Registration();
            } else if (button.getText().equals("Go Back")) {
                frame.dispose();
                new llogin();
            }
        }
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        UserStaff user = null;

        try {
            File file = new File("userStaffdata.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(frame, "No staff has registered yet!");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean In = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[1].equals(email) && parts[2].equals(password)) {
                    In = true;
                    user = new UserStaff(parts[0], parts[1], parts[2]);
                    break;
                }
            }
            reader.close();

            if (In) {
                JOptionPane.showMessageDialog(frame, "Login Successful!", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new StaffDash(user);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email or password!", "TRY AGAIN", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}