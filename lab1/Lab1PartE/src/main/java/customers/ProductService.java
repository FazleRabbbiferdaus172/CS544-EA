package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements  IProductService{
    @Autowired
    IProductDAO productDAO;
    @Autowired
    IEmailSender emailSender;

    public void createProduct(String name, String email) {
        Product product = new Product(name);
        productDAO.save(product);
        emailSender.sendEmail(email,  name + " , is created");
    }
}
