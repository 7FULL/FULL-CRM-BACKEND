package com.example.crm_backend.controllers;

import com.example.crm_backend.models.Client;
import com.example.crm_backend.services.AppointmentService;
import com.example.crm_backend.services.BillService;
import com.example.crm_backend.services.ClientService;
import com.example.crm_backend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pablo Hermida Gómez
 */

@RestController
@RequestMapping("/api/admin")
public class AdminController extends Controller{

    BillService billService;
    ClientService clientService;
    EmployeeService employeeService;
    AppointmentService appointmentService;

    @Autowired
    public AdminController(BillService billService, ClientService clientService, EmployeeService employeeService, AppointmentService appointmentService) {
        this.billService = billService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.appointmentService = appointmentService;
    }

    /**
     * Get all the bills from the database
     * @return  JSON with code 200 and the bills if they were found, or code 500 and a message if there was an error
     */
    @GetMapping("/getBills")
    public String getBills() {
        try {
            return ret(200, billService.getBills());
        } catch (Exception e) {
            return ret(500, "Error getting bills");
        }
    }

    /**
     * Get all the clients from the database
     * @return  JSON with code 200 and the clients if they were found, or code 500 and a message if there was an error
     */
    @GetMapping("/getClients")
    public String getClients() {
        try {
            return ret(200, clientService.getClients());
        } catch (Exception e) {
            return ret(500, "Error getting clients");
        }
    }

    /**
     * Get all the employees from the database
     * @return  JSON with code 200 and the employees if they were found, or code 500 and a message if there was an error
     */
    @GetMapping("/getEmployees")
    public String getEmployees() {
        try {
            return ret(200, employeeService.getAllEmployees());
        } catch (Exception e) {
            return ret(500, "Error getting employees");
        }
    }

    /**
     * Get all the appointments from the database
     * @return  JSON with code 200 and the appointments if they were found, or code 500 and a message if there was an error
     */
    @GetMapping("/getAppointments")
    public String getAppointments() {
        try {
            return ret(200, appointmentService.getAppointments());
        } catch (Exception e) {
            return ret(500, "Error getting appointments");
        }
    }
}
