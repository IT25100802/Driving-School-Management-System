package com.drivingschool.controller;

import com.drivingschool.entity.Admin;
import com.drivingschool.entity.Admin.Role;
import com.drivingschool.entity.UserSession;
import com.drivingschool.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AdminService adminService;

    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    // LOGIN PROCESS
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        Admin found = adminService.getAllAdmins()
                .stream()
                .filter(a -> a.getEmail().equals(email)
                        && a.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (found != null) {

            UserSession.getInstance().login(
                    found.getId(),
                    found.getName(),
                    found.getRole()
            );

            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid email or password!");
        return "auth/login";
    }

    // SIGNUP PAGE
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("admin", new Admin());
        return "auth/signup";
    }

    // SIGNUP PROCESS
    @PostMapping("/signup")
    public String signup(@ModelAttribute Admin admin,
                         @RequestParam String role,
                         Model model) {

        boolean exists = adminService.getAllAdmins()
                .stream()
                .anyMatch(a -> a.getEmail().equals(admin.getEmail()));

        if (exists) {
            model.addAttribute("error", "Email already exists!");
            model.addAttribute("roles", Role.values());
            return "auth/signup";
        }

        admin.setRole(Role.valueOf(role));
        adminService.addAdmin(admin);

        return "redirect:/login";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout() {
        UserSession.getInstance().logout();
        return "redirect:/login";
    }

    // HOME
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
}
