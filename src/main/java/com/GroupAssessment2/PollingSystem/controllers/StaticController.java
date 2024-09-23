package com.GroupAssessment2.PollingSystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

    @GetMapping("/")
    public String landingPage() {
        return "static"; // This will render src/main/resources/templates/dashboard.html
    }
}
