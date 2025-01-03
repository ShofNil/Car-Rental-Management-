import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Login and Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(450,200,500,300);


        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);


        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));


        Font f=new Font("Arial",Font.BOLD,14);

        JLabel loginUsernameLabel = new JLabel("     Username:");
        loginUsernameLabel.setFont(f);
        JTextField loginUsernameField = new JTextField();
        JLabel loginPasswordLabel = new JLabel("     Password:");
        loginPasswordLabel.setFont(f);
        JPasswordField loginPasswordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButtonFromLogin = new JButton("Go to Registration");


        loginPanel.add(loginUsernameLabel);
        loginPanel.add(loginUsernameField);
        loginPanel.add(loginPasswordLabel);
        loginPanel.add(loginPasswordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButtonFromLogin);


        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(5, 2));

        JLabel registerUsernameLabel = new JLabel("      Username:");
        registerUsernameLabel.setFont(f);
        JTextField registerUsernameField = new JTextField();
        JLabel registerPasswordLabel = new JLabel("      Password:");
        registerPasswordLabel.setFont(f);
        JPasswordField registerPasswordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("      Confirm Password:");
        confirmPasswordLabel.setFont(f);
        JPasswordField confirmPasswordField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        JButton backToLoginButton = new JButton("Back to Login");

        registerPanel.add(registerUsernameLabel);
        registerPanel.add(registerUsernameField);
        registerPanel.add(registerPasswordLabel);
        registerPanel.add(registerPasswordField);
        registerPanel.add(confirmPasswordLabel);
        registerPanel.add(confirmPasswordField);
        registerPanel.add(registerButton);
        registerPanel.add(backToLoginButton);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(registerPanel, "Register");

        frame.add(cardPanel);


        cardLayout.show(cardPanel, "Login");


        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String username = loginUsernameField.getText();
                String password = new String(loginPasswordField.getPassword());


                if(username.equals("admin") && password.equals("password")) {
                    JOptionPane.showMessageDialog(frame, "Login successful");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.");
                }
            }
        });


        registerButtonFromLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Register");
            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = registerUsernameField.getText();
                String password = new String(registerPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());


                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Username and Password fields cannot be empty.",
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match. Please try again.",
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(frame, "Registration successful",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cardPanel, "Login");

            }
        });


        backToLoginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "Login");
                }
            });


            frame.setVisible(true);
        }
    }
