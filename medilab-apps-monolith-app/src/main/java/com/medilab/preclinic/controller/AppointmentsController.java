package com.medilab.preclinic.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentsController {

    @GetMapping("/appointments")
    public String viewAppointments(){
        return "View Patients Appointments Here";
    }
}
