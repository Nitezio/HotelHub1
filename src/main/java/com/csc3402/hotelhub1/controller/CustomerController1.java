package com.csc3402.hotelhub1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController1 {

    // New route (renamed) to avoid conflict
    @GetMapping("/customer/home")
    public String home(Model model) {
        // You may add additional model attributes here later if needed
        return "customer_dashboard";  // Thymeleaf file: templates/customer_dashboard.html
    }
}
