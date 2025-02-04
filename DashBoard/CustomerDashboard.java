package DashBoard;

import Login.llogin;
import User.UserCustomer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Management.*;
public class CustomerDashboard extends JFrame {
    private final UserCustomer user;
    private CustomerVerificationManager c ;
    private Customer customer;
    private List<Car> cars;
    private List<BookedCar> bookedCars;
    private static final String CUSTOMER_DATA_FILE = "customer_data.txt";
    private Image backgroundImage; // Declare an Image variable

    public CustomerDashboard(UserCustomer user) {
        this.user = user;
        backgroundImage = new ImageIcon("bgdash.png").getImage(); // Change the path accordingly

        // Initialize car data and booked cars list
        initializeCarData();
        bookedCars = new ArrayList<>();
        loadCustomerData();

        // Main Dashboard
        setTitle("Customer Dashboard " + user.getName());
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // Add buttons for different functionalities
        JButton bookCarButton = new JButton("Book Cars");
        bookCarButton.setBackground(new Color(0, 0, 0));
        bookCarButton.setForeground(new Color(66, 126, 204));
        bookCarButton.setFont(new Font("Arial", Font.BOLD, 18));
        bookCarButton.setFocusable(false);
        bookCarButton.setBounds(200, 50, 200, 200);

        JButton showBookedCarsButton = new JButton("Show Booked Cars");
        showBookedCarsButton.setBackground(new Color(0, 0, 0));
        showBookedCarsButton.setForeground(new Color(66, 126, 204));
        showBookedCarsButton.setFont(new Font("Arial", Font.BOLD, 18));
        showBookedCarsButton.setFocusable(false);
        showBookedCarsButton.setBounds(480, 50, 200, 200);

        JButton returnCarButton = new JButton("Return Car");
        returnCarButton.setBackground(new Color(0, 0, 0));
        returnCarButton.setForeground(new Color(66, 126, 204));
        returnCarButton.setFont(new Font("Arial", Font.BOLD, 18));
        returnCarButton.setFocusable(false);
        returnCarButton.setBounds(60, 350, 200, 200);

        JButton noticesButton = new JButton("Notices");
        noticesButton.setBackground(new Color(0, 0, 0));
        noticesButton.setForeground(new Color(66, 126, 204));
        noticesButton.setFont(new Font("Arial", Font.BOLD, 18));
        noticesButton.setFocusable(false);
        noticesButton.setBounds(630, 350, 200, 200);

        JButton rulesButton = new JButton("Rules & Regulations");
        rulesButton.setBackground(new Color(0, 0, 0));
        rulesButton.setForeground(new Color(66, 126, 204));
        rulesButton.setFont(new Font("Arial", Font.BOLD, 18));
        rulesButton.setFocusable(false);
        rulesButton.setBounds(350, 350, 200, 200);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(0, 0, 0));
        logoutButton.setForeground(new Color(66, 126, 204));
        logoutButton.setFont(new Font("Arial", Font.BOLD, 18));
        logoutButton.setFocusable(false);
        logoutButton.setBounds(150, 600, 600, 40);

        add(bookCarButton);
        add(showBookedCarsButton);
        add(returnCarButton);
        add(noticesButton);
        add(rulesButton);
        add(logoutButton);

        // Action listeners for buttons
        bookCarButton.addActionListener(e -> openBookCarWindow());
        showBookedCarsButton.addActionListener(e -> openShowBookedCarsWindow());
        returnCarButton.addActionListener(e -> openReturnCarWindow());
        noticesButton.addActionListener(e -> checkNotices());
        rulesButton.addActionListener(e -> openRulesAndRegulationsWindow());
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            new llogin();
            dispose();
        });
    }

    private void openBookCarWindow() {
    /*  if (customer == null || !isVerified(customer)) {
            JOptionPane.showMessageDialog(this, "Please verify yourself first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }*/

        JFrame bookCarFrame = new JFrame("Book Cars");
        bookCarFrame.setSize(900, 700);
        bookCarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookCarFrame.setLocationRelativeTo(null);

        JPanel carPanel = new JPanel();
        carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS)); // Use vertical box layout for compact alignment

        // Dropdown values
        String[] years = {"2025", "2026", "2027"};
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        String[] hours = new String[24];
        for (int i = 0; i < 24; i++) {
            hours[i] = String.format("%02d", i);
        }

        for (Car car : cars) {
            JPanel carDetailPanel = new JPanel();
            carDetailPanel.setLayout(new BoxLayout(carDetailPanel, BoxLayout.Y_AXIS));
            carDetailPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            // Car details
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            JLabel carImageLabel = new JLabel();
            if (new File(car.getImagePath()).exists()) {
                carImageLabel.setIcon(new ImageIcon(new ImageIcon(car.getImagePath()).getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
            } else {
                carImageLabel.setText("No Image");
            }
            JLabel carDetailsLabel = new JLabel("<html>" + car.toString() + "</html>");
            JTextField quantityField = new JTextField("1", 5);

            topPanel.add(carImageLabel);
            topPanel.add(carDetailsLabel);
            topPanel.add(new JLabel("Quantity:"));
            topPanel.add(quantityField);

            // Booking type selection
            JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            JRadioButton dateBookingOption = new JRadioButton("Date-based Booking");
            JRadioButton hourlyBookingOption = new JRadioButton("Hourly Booking");
            ButtonGroup bookingTypeGroup = new ButtonGroup();
            bookingTypeGroup.add(dateBookingOption);
            bookingTypeGroup.add(hourlyBookingOption);

            middlePanel.add(dateBookingOption);
            middlePanel.add(hourlyBookingOption);

            // Date selection panel
            JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            datePanel.add(new JLabel("From:"));
            JComboBox<String> fromYearDropdown = new JComboBox<>(years);
            JComboBox<String> fromMonthDropdown = new JComboBox<>(months);
            JComboBox<String> fromDayDropdown = new JComboBox<>(days);
            datePanel.add(fromYearDropdown);
            datePanel.add(fromMonthDropdown);
            datePanel.add(fromDayDropdown);

            datePanel.add(new JLabel("To:"));
            JComboBox<String> toYearDropdown = new JComboBox<>(years);
            JComboBox<String> toMonthDropdown = new JComboBox<>(months);
            JComboBox<String> toDayDropdown = new JComboBox<>(days);
            datePanel.add(toYearDropdown);
            datePanel.add(toMonthDropdown);
            datePanel.add(toDayDropdown);

            // Hourly selection panel
            JPanel hourlyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            hourlyPanel.add(new JLabel("From:"));
            JComboBox<String> startHourDropdown = new JComboBox<>(hours);
            hourlyPanel.add(startHourDropdown);
            hourlyPanel.add(new JLabel("To:"));
            JComboBox<String> endHourDropdown = new JComboBox<>(hours);
            hourlyPanel.add(endHourDropdown);

            // Hide panels initially
            datePanel.setVisible(false);
            hourlyPanel.setVisible(false);

            dateBookingOption.addActionListener(e -> {
                datePanel.setVisible(true);
                hourlyPanel.setVisible(false);
            });

            hourlyBookingOption.addActionListener(e -> {
                datePanel.setVisible(false);
                hourlyPanel.setVisible(true);
            });

            // Payment option
            JCheckBox payByCashCheckbox = new JCheckBox("Pay by Cash");

            // Book button
            JButton bookButton = new JButton("Book");
            bookButton.addActionListener(e -> {
                try {
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    if (quantity <= 0 || quantity > car.getAvailable()) {
                        JOptionPane.showMessageDialog(bookCarFrame, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!payByCashCheckbox.isSelected()) {
                        JOptionPane.showMessageDialog(bookCarFrame, "Please select 'Pay by Cash' to complete booking!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int totalAmount = quantity * car.getRentPrice();
                    if (dateBookingOption.isSelected()) {
                        String fromDate = fromYearDropdown.getSelectedItem() + "-" +
                                fromMonthDropdown.getSelectedItem() + "-" +
                                fromDayDropdown.getSelectedItem();
                        String toDate = toYearDropdown.getSelectedItem() + "-" +
                                toMonthDropdown.getSelectedItem() + "-" +
                                toDayDropdown.getSelectedItem();
                        JOptionPane.showMessageDialog(bookCarFrame,
                                "Date Booking: From " + fromDate + " To " + toDate + " for " + quantity + " car(s). Total Amount: $" + totalAmount + ". Booking Confirmed.");
                    } else if (hourlyBookingOption.isSelected()) {
                        int startHour = Integer.parseInt(startHourDropdown.getSelectedItem().toString());
                        int endHour = Integer.parseInt(endHourDropdown.getSelectedItem().toString());
                        if (startHour >= endHour) {
                            JOptionPane.showMessageDialog(bookCarFrame, "Invalid hours!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        JOptionPane.showMessageDialog(bookCarFrame,
                                "Hourly Booking: From " + startHour + ":00 to " + endHour + ":00 for " + quantity + " car(s). Total Amount: $" + totalAmount + ". Booking Confirmed.");
                    } else {
                        JOptionPane.showMessageDialog(bookCarFrame, "Please select a booking type!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Add booked car to the bookedCars list
                    bookedCars.add(new BookedCar(car, quantity));

                    // Reduce the available quantity of the car
                    car.setAvailable(car.getAvailable() - quantity);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(bookCarFrame, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Add panels to carDetailPanel
            carDetailPanel.add(topPanel);
            carDetailPanel.add(middlePanel);
            carDetailPanel.add(datePanel);
            carDetailPanel.add(hourlyPanel);
            carDetailPanel.add(payByCashCheckbox);
            carDetailPanel.add(bookButton);

            carPanel.add(carDetailPanel);
        }

        JScrollPane scrollPane = new JScrollPane(carPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        bookCarFrame.add(scrollPane);
        bookCarFrame.setVisible(true);
    }

    private boolean isVerified(Customer customer) {
        // Logic to check the verification status from the verification file
        try (BufferedReader reader = new BufferedReader(new FileReader("customer_verification_requests.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(customer.getName()) && parts[3].equals("Approved")) {
                    return true; // Customer is verified
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Not verified
    }

    private void openShowBookedCarsWindow() {
        JFrame showFrame = new JFrame("Booked Cars");
        showFrame.setSize(900, 700);
        showFrame.setLayout(new BorderLayout());
        showFrame.setLocationRelativeTo(null);

        if (bookedCars.isEmpty()) {
            JLabel emptyLabel = new JLabel("No cars booked yet!", JLabel.CENTER);
            showFrame.add(emptyLabel, BorderLayout.CENTER);
        } else {
            JPanel bookedCarsPanel = new JPanel();
            bookedCarsPanel.setLayout(new BoxLayout(bookedCarsPanel, BoxLayout.Y_AXIS));

            for (BookedCar bookedCar : bookedCars) {
                JPanel bookedCarPanel = new JPanel(new BorderLayout(10, 10));
                JLabel carImageLabel = new JLabel();

                // Load car image or show "No Image"
                if (new File(bookedCar.getCar().getImagePath()).exists()) {
                    carImageLabel.setIcon(new ImageIcon(new ImageIcon(bookedCar.getCar().getImagePath())
                            .getImage()
                            .getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
                } else {
                    carImageLabel.setText("No Image");
                }

                // Car details and total amount
                JLabel bookedCarLabel = new JLabel(
                        "<html>" + bookedCar.getCar().toString() + "<br>Quantity: " +
                                bookedCar.getQuantity() + "<br>Total Amount: $" +
                                (bookedCar.getQuantity() * bookedCar.getCar().getRentPrice()) + "</html>"
                );

                bookedCarPanel.add(carImageLabel, BorderLayout.WEST);
                bookedCarPanel.add(bookedCarLabel, BorderLayout.CENTER);

                bookedCarsPanel.add(bookedCarPanel);
            }

            JScrollPane scrollPane = new JScrollPane(bookedCarsPanel);
            showFrame.add(scrollPane, BorderLayout.CENTER);
        }

        showFrame.setVisible(true);
    }

    private void openReturnCarWindow() {
        JFrame returnFrame = new JFrame("Return Cars");
        returnFrame.setSize(900, 700);
        returnFrame.setLayout(new BorderLayout());
        returnFrame.setLocationRelativeTo(null);

        if (bookedCars.isEmpty()) {
            JLabel emptyLabel = new JLabel("No cars booked yet!", JLabel.CENTER);
            returnFrame.add(emptyLabel, BorderLayout.CENTER);
        } else {
            JPanel returnCarsPanel = new JPanel();
            returnCarsPanel.setLayout(new BoxLayout(returnCarsPanel, BoxLayout.Y_AXIS));

            for (BookedCar bookedCar : bookedCars) {
                JPanel returnCarPanel = new JPanel(new BorderLayout(10, 10));
                JLabel carImageLabel = new JLabel();

                // Load car image or show "No Image"
                if (new File(bookedCar.getCar().getImagePath()).exists()) {
                    carImageLabel.setIcon(new ImageIcon(new ImageIcon(bookedCar.getCar().getImagePath())
                            .getImage()
                            .getScaledInstance(100, 60, Image.SCALE_SMOOTH)));
                } else {
                    carImageLabel.setText("No Image");
                }

                // Car details
                JLabel bookedCarLabel = new JLabel(bookedCar.toString());

                // Return button
                JButton returnButton = new JButton("Return");
                returnButton.addActionListener(e -> {
                    // Update car availability
                    Car car = bookedCar.getCar();
                    car.setAvailable(car.getAvailable() + bookedCar.getQuantity());
                    bookedCars.remove(bookedCar);

                    JOptionPane.showMessageDialog(returnFrame,
                            "Car returned successfully: " + car.getModel());
                    returnFrame.dispose();
                });

                returnCarPanel.add(carImageLabel, BorderLayout.WEST);
                returnCarPanel.add(bookedCarLabel, BorderLayout.CENTER);
                returnCarPanel.add(returnButton, BorderLayout.EAST);

                returnCarsPanel.add(returnCarPanel);
            }

            JScrollPane scrollPane = new JScrollPane(returnCarsPanel);
            returnFrame.add(scrollPane, BorderLayout.CENTER);
        }

        returnFrame.setVisible(true);
    }

    private void initializeCarData() {
        // Sample data for cars
        cars = new ArrayList<>();
        cars.add(new Car("Toyota Camry", "2025", 50, "toyota_camry.jpg", 10));
        cars.add(new Car("Honda Accord", "2024", 45, "honda_accord.jpg", 15));
        cars.add(new Car("BMW 5 Series", "2025", 100, "bmw_5_series.jpg", 5));
    }

    private void loadCustomerData() {
        File file = new File(CUSTOMER_DATA_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String name = reader.readLine();
                String nid = reader.readLine();
                String phone = reader.readLine();
                String address = reader.readLine();
                customer = new Customer(name, nid, phone, address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkNotices() {
        Date currentDate = new Date();
        for (BookedCar bookedCar : bookedCars) {
            if (bookedCar.getReturnDate().before(currentDate)) {
                JOptionPane.showMessageDialog(this,
                        "Notice: You have failed to return a car within the booked period!\n" +
                                "Please return the car immediately.",
                        "Notice", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void openRulesAndRegulationsWindow() {
        JFrame rulesFrame = new JFrame("Rules & Regulations");
        rulesFrame.setSize(900, 700);
        rulesFrame.setLocationRelativeTo(null);
        rulesFrame.setLayout(new BorderLayout());

        JTextArea rulesTextArea = new JTextArea(
                "Car Rental App - Rules and Regulations\n" +
                        "\n" +
                        "1. Eligibility Requirements\n" +
                        "\n" +
                        "1.1. Renters must be at least 21 years of age. Drivers under 25 may incur additional fees or restrictions.\n" +
                        "1.2. Renters must possess a valid government-issued driver’s license for the duration of the rental period.\n" +
                        "1.3. Renters must provide valid payment information and agree to a credit or debit card hold for security purposes.\n" +
                        "1.4. Renters are responsible for ensuring that their driving privileges are not suspended, revoked, or otherwise invalidated.\n" +
                        "\n" +
                        "2. Vehicle Usage Policies\n" +
                        "\n" +
                        "2.1. The vehicle must be used for personal or business purposes only and not for illegal activities, subleasing, or ridesharing platforms unless explicitly allowed.\n" +
                        "2.2. The renter must comply with all traffic laws and regulations during the rental period.\n" +
                        "2.3. Smoking, vaping, or carrying hazardous materials in the vehicle is strictly prohibited.\n" +
                        "2.4. Pets are only allowed in vehicles designated as pet-friendly, and renters must return the vehicle clean and free of pet-related damage.\n" +
                        "2.5. The vehicle must not exceed the manufacturer’s recommended weight capacity.\n" +
                        "\n" +
                        "3. Booking and Cancellation Policies\n" +
                        "\n" +
                        "3.1. Renters must provide accurate and truthful information when making a booking.\n" +
                        "3.2. A non-refundable booking fee may apply for reservations.\n" +
                        "3.3. Cancellations must be made at least 24 hours before the scheduled pickup time to avoid additional charges.\n" +
                        "3.4. No-shows or late cancellations will result in a penalty fee as outlined in the pricing policy.\n" +
                        "3.5. Changes to booking details are subject to availability and may incur modification fees.\n" +
                        "\n" +
                        "4. Payment Terms\n" +
                        "\n" +
                        "4.1. All payments must be made via approved payment methods listed in the app.\n" +
                        "4.2. A security deposit may be required at the time of booking or vehicle pickup.\n" +
                        "4.3. Late returns will incur hourly or daily late fees as specified in the app’s pricing structure.\n" +
                        "4.4. Renters are responsible for all tolls, parking tickets, traffic violations, and other fines incurred during the rental period.\n" +
                        "4.5. Additional charges will apply for excess mileage, fuel shortages, or failure to return the vehicle in the condition it was provided.\n" +
                        "\n" +
                        "5. Insurance and Liability\n" +
                        "\n" +
                        "5.1. Renters are required to have valid auto insurance covering the rental vehicle, or they must purchase coverage through the app.\n" +
                        "5.2. The renter is liable for all damages, theft, or loss of the vehicle that occur during the rental period unless insurance coverage applies.\n" +
                        "5.3. In the event of an accident, renters must notify the app and local authorities immediately and provide all necessary documentation, including an accident report.\n" +
                        "5.4. Personal belongings left in the vehicle are not covered by the app or insurance.\n" +
                        "\n" +
                        "6. Vehicle Pickup and Return\n" +
                        "\n" +
                        "6.1. Renters must inspect the vehicle for pre-existing damage before driving and report any issues via the app.\n" +
                        "6.2. Vehicles must be returned to the designated location at the agreed-upon time.\n" +
                        "6.3. The vehicle must be returned clean and with the same fuel level as when it was rented.\n" +
                        "6.4. A fee will apply for returning the vehicle to an unauthorized location or failing to meet return conditions.\n" +
                        "\n" +
                        "7. Prohibited Activities\n" +
                        "\n" +
                        "7.1. Using the vehicle for off-road driving, racing, towing, or pushing other vehicles is prohibited.\n" +
                        "7.2. Transporting illegal goods, weapons, or substances is strictly forbidden.\n" +
                        "7.3. Allowing unauthorized drivers to operate the vehicle is not permitted.\n" +
                        "\n" +
                        "8. Maintenance and Repairs\n" +
                        "\n" +
                        "8.1. Renters must notify the app immediately if the vehicle experiences any mechanical issues.\n" +
                        "8.2. Renters must not attempt unauthorized repairs or modifications to the vehicle.\n" +
                        "8.3. All costs for damage caused by negligence or misuse will be charged to the renter.\n" +
                        "\n" +
                        "9. Privacy and Data Protection\n" +
                        "\n" +
                        "9.1. The app collects and uses personal information in accordance with its Privacy Policy.\n" +
                        "9.2. Location tracking may be enabled for safety and theft prevention purposes.\n" +
                        "9.3. Renters must not disable or tamper with GPS or tracking devices installed in the vehicle.\n" +
                        "\n" +
                        "10. Dispute Resolution and Termination\n" +
                        "\n" +
                        "10.1. Any disputes must be reported to the app’s support team within 7 days of the incident.\n" +
                        "10.2. The app reserves the right to suspend or terminate accounts for violating the rules and regulations.\n" +
                        "10.3. Renters may face legal action for fraud, theft, or willful damage to vehicles.\n" +
                        "\n" +
                        "11. Changes to Policies\n" +
                        "\n" +
                        "11.1. The app reserves the right to update these rules and regulations at any time.\n" +
                        "11.2. Renters will be notified of significant changes via the app or registered email.\n" +
                        "\n" +
                        " By using the car rental app, you agree to adhere to these rules and regulations." +
                        "\n Violations may result in fines, account suspension, or legal consequences."
        );
        rulesTextArea.setEditable(false);
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 20));  // Adjusting the font size
        rulesFrame.add(new JScrollPane(rulesTextArea), BorderLayout.CENTER);
        rulesFrame.setVisible(true);
    }

   

class Customer {
    private String name;
    private String nid;
    private String phone;
    private String address;

    public Customer(String name, String nid, String phone, String address) {
        this.name = name;
        this.nid = nid;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getNid() {
        return nid;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}

class Car {
    private String model;
    private String year;
    private int rentPrice;
    private String imagePath;
    private int available;

    public Car(String model, String year, int rentPrice, String imagePath, int available) {
        this.model = model;
        this.year = year;
        this.rentPrice = rentPrice;
        this.imagePath = imagePath;
        this.available = available;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return model + " (" + year + ") - $" + rentPrice + " per day | Available: " + available;
    }
}

class BookedCar {
    private Car car;
    private int quantity;

    public BookedCar(Car car, int quantity) {
        this.car = car;
        this.quantity = quantity;
    }

    public Car getCar() {
        return car;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return car.getModel() + " | Quantity: " + quantity;
    }

    public Date getReturnDate() {
        return null;
    }
}}