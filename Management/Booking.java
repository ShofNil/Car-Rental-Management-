package Management;

public class Booking {
    private String customerName;
    private String vehicleType;
    private String vehicleModel;
    private String bookingStatus;

    public Booking(String customerName, String vehicleType, String vehicleModel, String bookingStatus) {
        this.customerName = customerName;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.bookingStatus = bookingStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}