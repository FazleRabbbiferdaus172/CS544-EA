package customers;

public class Supplier {
    private int supplierNumber;
    private String name;
    private String phone;

    // Constructors, getters, setters
    public Supplier(int supplierNumber, String name, String phone) {
        this.supplierNumber = supplierNumber;
        this.name = name;
        this.phone = phone;
    }
    public String toString() {
        return "Supplier number: " + supplierNumber + ", name: " + name + ", Phone: " + phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSupplierNumber() {
        return supplierNumber;
    }
}
