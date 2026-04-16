package com.drivingschool.controller;

import com.drivingschool.entity.Admin;
import com.drivingschool.entity.Admin.Role;
import com.drivingschool.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    // LIST ADMINS
    @GetMapping("/list")
    public String listAdmins(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "admin/list";
    }

    // SHOW REGISTER FORM
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("roles", Role.values());
        return "admin/register";
    }

    // REGISTER ADMIN (CREATE)
    @PostMapping("/register")
    public String registerAdmin(@ModelAttribute Admin admin,
                                @RequestParam String role) {

        admin.setRole(Role.valueOf(role));
        adminService.addAdmin(admin);

        return "redirect:/admin/list";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {

        Admin admin = adminService.getAdminById(id);

        if (admin == null) {
            return "redirect:/admin/list";
        }

        model.addAttribute("admin", admin);
        model.addAttribute("roles", Role.values());

        return "admin/edit";
    }

    // UPDATE ADMIN
    @PostMapping("/edit")
    public String updateAdmin(@ModelAttribute Admin admin,
                              @RequestParam String role) {

        admin.setRole(Role.valueOf(role));
        adminService.updateAdmin(admin);

        return "redirect:/admin/list";
    }

    // DELETE ADMIN
    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
        return "redirect:/admin/list";
    }

    // SEARCH ADMIN
    @GetMapping("/search")
    public String searchAdmin(@RequestParam String name, Model model) {
        model.addAttribute("admins", adminService.searchByName(name));
        return "admin/list";
    }
}
