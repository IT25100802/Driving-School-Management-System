package com.drivingschool.controller;

import com.drivingschool.entity.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        // session check
        if (!UserSession.getInstance().isLoggedIn()) {
            return "redirect:/login";
        }

        model.addAttribute("userName", UserSession.getInstance().getUserName());
        model.addAttribute("role", UserSession.getInstance().getRole());

        return "auth/dashboard"; // MUST MATCH FILE LOCATION
    }
}