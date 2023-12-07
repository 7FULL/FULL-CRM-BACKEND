package com.example.crm_backend.controllers;

import com.example.crm_backend.models.Bill;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.network.BillRequest;
import com.example.crm_backend.services.BillService;
import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Bill controller
 */
@RestController
@RequestMapping("/api/bill")
public class BillController extends Controller{

    BillService service;

    @Autowired
    public BillController(BillService service) {
        this.service = service;
    }

    /**
     * Add a bill to the database
     * @param request   BillRequest object with the bill to add, and the employee and client that make it up
     * @return          JSON with code 200 if the bill was added, or code 500 if there was an error and a message
     */
    @PostMapping("/addBill")
    public String addBill(@RequestBody BillRequest request) {
        try {
            Bill bill = request.getBill();
            Employee employee = request.getEmployee();
            Client client = request.getClient();

            service.addBill(bill, employee, client);
        } catch (Exception e) {
            Sentry.captureException(e);
            return ret(500, "Error inserting bill");
        }

        return ret(200, "Bill inserted");
    }

    /**
     * Pin Pon
     * @return Pon
     */
    @GetMapping("/pin")
    public String pin() {
        //Sentry.captureException(new Exception("Pon"));
        return ret(200, "Pon");
    }
}
