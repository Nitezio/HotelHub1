package com.csc3402.hotelhub1.controller;

import com.csc3402.hotelhub1.model.Package;
import com.csc3402.hotelhub1.repository.PackageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff/packages")
public class StaffPackageController {

    private final PackageRepository packageRepo;

    @Autowired
    public StaffPackageController(PackageRepository packageRepo) {
        this.packageRepo = packageRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("packages", packageRepo.findAll());
        return "packages"; // maps to templates/packages.html
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("aPackage", new Package());
        return "package_form"; // maps to templates/package_form.html
    }

    @PostMapping("/new")
    public String create(@Valid @ModelAttribute("aPackage") Package aPackage,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "package_form";
        }
        packageRepo.save(aPackage);
        return "redirect:/staff/packages";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        var pkg = packageRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package Id:" + id));
        model.addAttribute("aPackage", pkg);
        return "package_view"; // maps to templates/package_view.html
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        var pkg = packageRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package Id:" + id));
        model.addAttribute("aPackage", pkg);
        return "package_form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("aPackage") Package aPackage,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "package_form";
        }
        aPackage.setPackageId(id);
        packageRepo.save(aPackage);
        return "redirect:/staff/packages";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        packageRepo.deleteById(id);
        return "redirect:/staff/packages";
    }
}
