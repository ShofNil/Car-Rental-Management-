package Login;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class llogin extends frameDesign {
    private JFrame firstpage;
    private JButton customer, Admin, reg, staff;
    private JLabel l, l1;

    public llogin() {
        firstpage = base_frame(firstpage, "Car Rental Management System", 1280, 720, null);

        // Assign buttons correctly
        customer = button_design(customer, "Customer Login", 820, 550, 200, 40, Color.ORANGE, Color.BLACK);
        reg = button_design(reg, "Register", 590, 650, 200, 40, Color.WHITE, Color.BLACK);
        Admin = button_design(Admin, "Admin Login", 350, 550, 200, 40, Color.CYAN, Color.BLACK);
        staff = button_design(staff, "Staff Login", 590, 550, 200, 40, Color.CYAN, Color.BLACK);


        // Add action listeners to the buttons
        customer.addActionListener(e -> {
            firstpage.dispose();
            new LogCustomer();
        });

        reg.addActionListener(e -> {
            firstpage.dispose();
            new Registration();
        });

        Admin.addActionListener(e -> {
            firstpage.dispose();
            new LogAdmin();
        });

        staff.addActionListener(e -> {
            firstpage.dispose();
            new LogStaff();
        });



        l1 = new JLabel("                Don't have an account yet ?");
        l = new JLabel("                          ভাড়া হবে!!");

        l.setForeground(Color.WHITE);
        l1.setFont(new Font("Arial", Font.BOLD, 13));
        l1.setForeground(Color.WHITE);

        l.setBounds(550, 480, 600, 50);
        l1.setBounds(540, 590, 600, 50);

        l.setOpaque(false);
        l1.setOpaque(false);

        ImageIcon background = new ImageIcon("car.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, -150, 1280, 720);
        firstpage.add(backgroundLabel);

        backgroundLabel.add(staff);
        backgroundLabel.add(Admin);
        backgroundLabel.add(customer);
        backgroundLabel.add(reg);
        backgroundLabel.add(l);
        backgroundLabel.add(l1);

        firstpage.setVisible(true);
        firstpage.setLocationRelativeTo(null);
        firstpage.getContentPane().setBackground(Color.BLACK);

        ImageIcon icon = new ImageIcon("icon.png");  // icon
        firstpage.setIconImage(icon.getImage());
    }
}