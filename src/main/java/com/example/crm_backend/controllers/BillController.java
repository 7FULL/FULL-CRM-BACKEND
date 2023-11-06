package com.example.crm_backend.controllers;

import com.example.crm_backend.models.Bill;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.network.BillRequest;
import com.example.crm_backend.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@RestController
@RequestMapping("/api/bill")
public class BillController extends Controller{

    BillService service;

    @Autowired
    public BillController(BillService service) {
        this.service = service;
    }

    @PostMapping("/addBill")
    public String addBill(@RequestBody BillRequest request) {
        Bill bill = request.getBill();
        Employee employee = request.getEmployee();
        Client client = request.getClient();

        service.addBill(bill, employee, client);
        return ret(200, "Bill inserted");
    }
}