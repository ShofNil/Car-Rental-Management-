package Management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RentalManager {
    private static final String RENTAL_FILE_PATH = "rentals.txt"; // Path to the rentals data file
    private List<Rental> rentals;

    public RentalManager() {
        rentals = new ArrayList<>();
        loadRentals(); // Load rentals from the file when the manager is created
    }

    // Load rentals from the file
    private void loadRentals() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RENTAL_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rentalData = line.split(",");
                if (rentalData.length >= 4) {
                    rentals.add(new Rental(rentalData[0], rentalData[1], rentalData[2], rentalData[3])); // Assuming Rental has a constructor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all rentals
    public List<String[]> getAllRentals() {
        List<String[]> rentalList = new ArrayList<>();
        for (Rental rental : rentals) {
            rentalList.add(new String[]{
                rental.getCustomerName(),
                rental.getVehicleType(),
                rental.getVehicleModel(),
                rental.getRentalStatus()
            });
        }
        return rentalList;
    }

    // Return a vehicle
    public void returnVehicle(String customerName, String vehicleType, String vehicleModel) {
        for (Rental rental : rentals) {
            if (rental.getCustomerName().equals(customerName) &&
                rental.getVehicleType().equals(vehicleType) &&
                rental.getVehicleModel().equals(vehicleModel)) {
                rental.setRentalStatus("Returned"); // Update the rental status
                saveRentals(); // Save updated rentals to the file
                return;
            }
        }
    }

    // Save rentals back to the file
    private void saveRentals() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RENTAL_FILE_PATH))) {
            for (Rental rental : rentals) {
                writer.write(rental.getCustomerName() + "," +
                             rental.getVehicleType() + "," +
                             rental.getVehicleModel() + "," +
                             rental.getRentalStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // (Optional) Add a new rental
    public void addRental(String customerName, String vehicleType, String vehicleModel, String rentalStatus) {
               Rental newRental = new Rental(customerName, vehicleType, vehicleModel, rentalStatus);
                rentals.add(newRental);
    }


}