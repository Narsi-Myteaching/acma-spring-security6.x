package com.medilab.preclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/patients")
public class PatientsController {

    @RequestMapping
    public String viewPatientsBoard(Model model) {
        System.out.println("i am in Patients");
        return "patient/patients";
    }

}
