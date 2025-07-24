package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private  ProductDAO productDAO;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.clearDB();
		productDAO.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());
		Product product = new Product(101, "A", "100");
		Supplier supplier = new Supplier(201, "Visa", "11/23");
		product.setSupplier(supplier);
		productDAO.save(product);
		product = new Product(102, "B", "100");
		supplier = new Supplier(202, "Hisa", "12/24");
		product.setSupplier(supplier);
		productDAO.save(product);
		System.out.println(productDAO.getAllProducts());
		System.out.println(productDAO.findByProductNumber(101));
		System.out.println(productDAO.findByProductName("B"));
		productDAO.removeProduct(102);
		System.out.println(productDAO.getAllProducts());
	}
}
