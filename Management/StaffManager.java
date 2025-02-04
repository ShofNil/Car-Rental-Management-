package Management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import User.UserStaff;

public class StaffManager {
    private static final String STAFF_FILE_PATH = "staff_data.txt"; // Path to the staff data file
    private List<UserStaff> staffList;

    public StaffManager() {
        staffList = new ArrayList<>();
        loadStaff(); // Load staff from the file when the manager is created
    }

    // Load staff from the file
    private void loadStaff() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] staffData = line.split(",");
                if (staffData.length >= 3) {
                    staffList.add(new UserStaff(staffData[0], staffData[1], staffData[2])); // Assuming UserStaff has a constructor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a new staff member
    public void addStaff(UserStaff staff) {
        staffList.add(staff);
        saveStaff(); // Save updated staff to the file
    }

    // Remove a staff member
    public void removeStaff(String email) {
        staffList.removeIf(staff -> staff.getEmail().equals(email));
        saveStaff(); // Save updated staff to the file
    }

    // Save staff back to the file
    private void saveStaff() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STAFF_FILE_PATH))) {
            for (UserStaff _  : staffList) {
                writer.write(((UserStaff) staffList).getName() + "," + ((UserStaff) staffList).getEmail() + "," + ((UserStaff) staffList).getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all staff members
    public List<UserStaff> getAllStaff() {
        return staffList;
    }

    // Get the count of registered staff
    public int getStaffCount() {
        return staffList.size();
    }
}