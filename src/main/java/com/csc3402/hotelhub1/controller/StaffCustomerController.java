package com.csc3402.hotelhub1.controller;

import com.csc3402.hotelhub1.model.Customer;
import com.csc3402.hotelhub1.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff/customers")
public class StaffCustomerController {

    private final CustomerRepository customerRepo;

    @Autowired
    public StaffCustomerController(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    // GET /staff/customers → templates/list.html
    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", customerRepo.findAll());
        return "list";
    }

    // GET /staff/customers/new → templates/form.html  (you’ll need to create form.html)
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "form";
    }

    @PostMapping("/new")
    public String create(@Valid @ModelAttribute Customer customer, BindingResult br) {
        if (br.hasErrors()) {
            return "form";
        }
        customerRepo.save(customer);
        return "redirect:/staff/customers";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Customer cust = customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", cust);
        return "view";  // you'll need view.html
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Customer cust = customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", cust);
        return "form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Customer customer,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "form";
        }
        customer.setCustomerId(id);
        customerRepo.save(customer);
        return "redirect:/staff/customers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        customerRepo.deleteById(id);
        return "redirect:/staff/customers";
    }
}
