package com.example.crm_backend.controllers;

import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.services.AppointmentService;
import com.example.crm_backend.services.BillService;
import com.example.crm_backend.services.ClientService;
import com.example.crm_backend.services.EmployeeService;
import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for the endpoints of the admin
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
            Sentry.captureException(e);
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
            Sentry.captureException(e);
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
            Sentry.captureException(e);
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
            Sentry.captureException(e);
            return ret(500, "Error getting appointments");
        }
    }

    @DeleteMapping("/deleteBill")
    public String deleteBill(String id) {
        try {
            billService.deleteBill(id);
        } catch (Exception e) {
            Sentry.captureException(e);
            return ret(500, "Error deleting bill");
        }

        return ret(200, "Bill deleted");
    }

    @DeleteMapping("/deleteClient")
    public String deleteClient(String id) {
        try {
            clientService.deleteClient(id);
        } catch (Exception e) {
            Sentry.captureException(e);
            return ret(500, "Error deleting client");
        }

        return ret(200, "Client deleted");
    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(String id) {
        try {
            employeeService.deleteEmployee(id);
        } catch (Exception e) {
            Sentry.captureException(e);
            return ret(500, "Error deleting employee");
        }

        return ret(200, "Employee deleted");
    }

    @DeleteMapping("/deleteAppointment")
    public String deleteAppointment(String id) {
        try {
            appointmentService.deleteAppointment(id);
        } catch (Exception e) {
            Sentry.captureException(e);
            return ret(500, "Error deleting appointment");
        }

        return ret(200, "Appointment deleted");
    }

    @GetMapping("/pin")
    public String pin() {
        return ret(200, "Pon");
    }

    @PostMapping("/addClient")
    public String addClient(@RequestBody Client client) {
        try {
            clientService.addClient(client);
        } catch (Exception e) {
            Sentry.captureException(e);
            return ret(500, "Error inserting client");
        }

        return ret(200, "Client inserted");
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
        } catch (Exception e) {
            Sentry.captureException(e);
            return ret(500, "Error inserting employee");
        }

        return ret(200, "Employee inserted");
    }
}
