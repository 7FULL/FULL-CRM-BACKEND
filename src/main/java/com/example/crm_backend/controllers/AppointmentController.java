package com.example.crm_backend.controllers;

import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.services.AppointmentService;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController extends Controller{

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/addAppointment")
    public String addAppointment(Employee employee, Client client, Appointment appointment) {
        appointmentService.addAppointment(appointment, employee, client);

        return ret(200, "Appointment inserted");
    }

    @GetMapping("/addExample")
    public String addExample() {
        Appointment a = new Appointment(new Date(), "admin", null, null);

        appointmentService.addAppointment(a);

        return ret(200, "Appointment inserted");
    }

    @GetMapping("/pin")
    public String pinPon() {
        return ret(200, "pon");
    }
}

