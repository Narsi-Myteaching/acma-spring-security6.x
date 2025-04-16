package com.medilab.preclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationGlobalErrorController {

    @RequestMapping("/AuthzError")
    public String getErrorPage(Model model) {
        System.out.println("Global Error Page Controller");
        model.addAttribute("authzErrorMsg", "Not Authrized to do this Action");
        return "errors/authzError";
    }
}
