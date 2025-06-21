package com.csc3402.hotelhub1.controller;

import com.csc3402.hotelhub1.model.Customer;
import com.csc3402.hotelhub1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired private CustomerService customerService;

    @GetMapping("/") public String root() { return "redirect:/login"; }
    @GetMapping("/login") public String loginPage() { return "login"; }

    @GetMapping("/signup")
    public String showSignup(Model m) {
        m.addAttribute("customer", new Customer());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute Customer c, RedirectAttributes ra) {
        customerService.save(c);
        ra.addFlashAttribute("successMessage","Registered! Please login.");
        return "redirect:/login";
    }
}