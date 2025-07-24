package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from product",namedParameters);
    }

    public void save(Product product) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("", product.getProductNumber());
        namedParameters.put("name", customer.getName());
        namedParameters.put("email", customer.getEmail());
        namedParameters.put("phone", customer.getPhone());
        jdbcTemplate.update("INSERT INTO customer VALUES ( :customernumber, :name, :email, :phone)",namedParameters);

        }


    public Customer getCustomer(int customerNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customerNumber", customerNumber);
        Customer customer = jdbcTemplate.queryForObject("SELECT * FROM customer WHERE "
                        + "customerNumber =:customerNumber ",
                namedParameters,
                (rs, rowNum) -> new Customer( rs.getInt("customerNumber"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")));

        CreditCard creditCard = getCreditCardForCustomer(customer.getCustomerNumber());
        customer.setCreditCard(creditCard);
        return customer;

    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customer",
                new HashMap<String, Customer>(),
                (rs, rowNum) -> new Customer( rs.getInt("customerNumber"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")));

        for (Customer customer: customers){
            CreditCard creditCard = getCreditCardForCustomer(customer.getCustomerNumber());
            customer.setCreditCard(creditCard);
        }
        return customers;
    }

    CreditCard getCreditCardForCustomer(int customerNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customerNumber", customerNumber);
        CreditCard creditCard = jdbcTemplate.queryForObject("SELECT * FROM creditcard WHERE "
                        + "customerNumber =:customerNumber ",
                namedParameters,
                (rs, rowNum) -> new CreditCard( rs.getString("cardnumber"),
                        rs.getString("type"),
                        rs.getString("validationDate")));

        return creditCard;
    }
}
