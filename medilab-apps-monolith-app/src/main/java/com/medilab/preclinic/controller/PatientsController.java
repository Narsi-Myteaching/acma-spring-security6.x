package com.medilab.preclinic.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/patients")
@PreAuthorize("hasRole('PATIENTS')")
public class PatientsController {

    @RequestMapping
    public String viewPatientsBoard(Model model) {
        System.out.println("i am in Patients");
        return "patient/patients";
    }

}
