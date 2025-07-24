package customers;

public class Product {
    private  int productNumber;
    private String name;
    private String price;
    private Supplier supplier;

    public Product(int productNumber, String name, String price) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;

    }

    @Override
    public String toString() {
        return "Product number: " + productNumber + ", name: " + name + ", Price: " + price+ ", Supplier: "+ supplier;
    }

    public int getProductNumber() {return productNumber;}
    public String getName() {return name;}
    public String getPrice() {return price;}
    public void setName(String name) {this.name = name;}
    public  void setPrice(String price) {this.price = price;}
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    public Supplier getSupplier() {
        return this.supplier;
    }
 }
