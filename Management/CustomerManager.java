package Management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private static final String CUSTOMER_FILE_PATH = "customers.txt"; // Path to the customers data file
    private List<Customer> customers;

    public CustomerManager() {
        customers = new ArrayList<>();
        loadCustomers(); // Load customers from the file when the manager is created
    }

    // Load customers from the file
    private void loadCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] customerData = line.split(",");
                if (customerData.length >= 3) {
                    customers.add(new Customer(customerData[0], customerData[1], customerData[2])); // Assuming Customer has a constructor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all customers
    public List<String[]> getAllCustomers() {
        List<String[]> customerList = new ArrayList<>();
        for (Customer customer : customers) {
            customerList.add(new String[]{
                    customer.getName(),
                    customer.getEmail(),
                    customer.getPhone() // Assuming Customer has a getPhone method
            });
        }
        return customerList;
    }

    // (Optional) Add a new customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
        saveCustomers(); // Save updated customers to the file
    }

    // Save customers back to the file
    private void saveCustomers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE_PATH))) {
            for (Customer customer : customers) {
                writer.write(customer.getName() + "," +
                        customer.getEmail() + "," +
                        customer.getPhone());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}