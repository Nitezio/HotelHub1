package com.csc3402.hotelhub1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffController {
    @GetMapping("/staff/dashboard")
    public String dashboard(Model model) {
        return "staff_dashboard";
    }
}