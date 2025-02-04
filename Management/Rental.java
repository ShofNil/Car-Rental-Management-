package Management;

public class Rental {
    private String customerName;
    private String vehicleType;
    private String vehicleModel;
    private String rentalStatus;

    public Rental(String customerName, String vehicleType, String vehicleModel, String rentalStatus) {
        this.customerName = customerName;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.rentalStatus = rentalStatus;
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

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}