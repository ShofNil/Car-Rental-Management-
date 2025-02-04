package Management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleManager {
    private static final String VEHICLE_FILE_PATH = "vehicles.txt"; // Path to the vehicles data file
    private List<Vehicle> vehicles;

    public VehicleManager() {
        vehicles = new ArrayList<>();
        loadVehicles(); // Load vehicles from the file when the manager is created
    }

    // Load vehicles from the file
    private void loadVehicles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(VEHICLE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] vehicleData = line.split(",");
                if (vehicleData.length >= 4) {
                    vehicles.add(new Vehicle(vehicleData[0], vehicleData[1], vehicleData[2], vehicleData[3])); // Assuming Vehicle has a constructor
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading vehicles: " + e.getMessage());
        }
    }

    // Add a new vehicle
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        saveVehicles(); // Save updated vehicles to the file
    }

    // Remove a vehicle
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
        saveVehicles(); // Save updated vehicles to the file
    }

    // Get available vehicles
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getStatus().equalsIgnoreCase("Available")) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    // Save vehicles back to the file
    private void saveVehicles() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLE_FILE_PATH))) {
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.getType() + "," +
                        vehicle.getModel() + "," +
                        vehicle.getImagePath() + "," +
                        vehicle.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving vehicles: " + e.getMessage());
        }
    }
}