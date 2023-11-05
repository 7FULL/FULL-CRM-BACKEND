package com.example.crm_backend.services;

import com.example.crm_backend.models.Bill;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.repository.BillRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Service
public class BillService {

    private EmployeeService employeeService;

    private ClientService clientService;

    private BillRespository billRespository;

    @Autowired
    public BillService(EmployeeService employeeService, ClientService clientService, BillRespository billRespository) {
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.billRespository = billRespository;
    }

    public void addBill(Bill bill, Employee employee, Client client){
        bill = billRespository.save(bill);

        employee = employeeService.addBill(employee, bill);

        client = clientService.addBill(client, bill);

        bill.setEmployeeID(employee.getId());
        bill.setClientID(client.getId());

        billRespository.save(bill);
    }
}
