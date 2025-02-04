package Management;

public class Vehicle {
    private String type; // e.g., Car, Truck, etc.
    private String model; // e.g., Toyota Camry
    private String imagePath; // Path to the vehicle image
    private String status; // e.g., Available, Rented, Maintenance

    public Vehicle(String type, String model, String imagePath, String status) {
        this.type = type;
        this.model = model;
        this.imagePath = imagePath;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}