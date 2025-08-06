package controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/shop")
    public String getShopInfo() {
        return "Welcome to the shop! Everyone can see this.";
    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('finance') or hasRole('sales')")
    public String getOrders() {
        return "Here are the company orders. (Visible to all employees)";
    }

    @GetMapping("/payments")
    @PreAuthorize("hasRole('finance')")
    public String getPayments() {
        return "Here are the financial payment details. (Visible only to finance employees)";
    }
}
