package com.csc3402.hotelhub1.controller;

import com.csc3402.hotelhub1.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffReportController {

    private final StaffService staffService;

    @Autowired
    public StaffReportController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staff/reports")
    public String reports(Model model) {
        model.addAttribute("totalCustomers", staffService.getTotalCustomers());
        model.addAttribute("totalBookings", staffService.getTotalBookings());
        model.addAttribute("newBookings", staffService.getNewBookings());
        model.addAttribute("completedBookings", staffService.getCompletedBookings());
        model.addAttribute("cancelledBookings", staffService.getCancelledBookings());
        model.addAttribute("totalRevenue", staffService.getTotalRevenue());
        return "reports"; // templates/reports.html
    }
}
