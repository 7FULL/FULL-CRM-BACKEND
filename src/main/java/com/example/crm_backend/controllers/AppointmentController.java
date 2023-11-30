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

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Appointment controller
 */
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController extends Controller{

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Get all the appointments
     * @param employee      The employee to add the appointment to
     * @param client        The client to add the appointment to
     * @param appointment   The appointment to add and save to the database
     * @return              JSON with code 200 if everything is ok, 500 if not and a message
     */
    @PostMapping("/addAppointment")
    public String addAppointment(Employee employee, Client client, Appointment appointment) {
        try{
            appointmentService.addAppointment(appointment, employee, client);
        }
        catch (Exception e){
            return ret(500, "Error inserting appointment");
        }

        return ret(200, "Appointment inserted");
    }

    /**
     * Pin Pon
     * @return Pon
     */
    @GetMapping("/pin")
    public String pinPon() {
        return ret(200, "pon");
    }
}

