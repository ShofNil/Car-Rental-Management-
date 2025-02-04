package Management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private static final String BOOKING_FILE_PATH = "bookings.txt"; // Path to the bookings data file
    private List<Booking> bookings;

    public BookingManager() {
        bookings = new ArrayList<>();
        loadBookings(); // Load bookings from the file when the manager is created
    }

    // Load bookings from the file
    private void loadBookings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKING_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookingData = line.split(",");
                if (bookingData.length >= 4) {
                    bookings.add(new Booking(bookingData[0], bookingData[1], bookingData[2], bookingData[3])); // Assuming Booking has a constructor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all bookings
    public List<String[]> getAllBookings() {
        List<String[]> bookingList = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingList.add(new String[]{
                    booking.getCustomerName(),
                    booking.getVehicleType(),
                    booking.getVehicleModel(),
                    booking.getBookingStatus()
            });
        }
        return bookingList;
    }

    // (Optional) Add a new booking
    public void addBooking(Booking booking) {
        bookings.add(booking);
        saveBookings(); // Save updated bookings to the file
    }

    // Save bookings back to the file
    private void saveBookings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKING_FILE_PATH))) {
            for (Booking booking : bookings) {
                writer.write(booking.getCustomerName() + "," +
                        booking.getVehicleType() + "," +
                        booking.getVehicleModel() + "," +
                        booking.getBookingStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}