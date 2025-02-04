package Management;

public class CustomerVerificationRequest {
    private String customerName;
    private String nid;
    private String phone;
    private String status; // Approved or Pending

    public CustomerVerificationRequest(String customerName, String nid, String phone) {
        this.customerName = customerName;
        this.nid = nid;
        this.phone = phone;
        this.status = "Pending"; // Default status
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getNid() {
        return nid;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}