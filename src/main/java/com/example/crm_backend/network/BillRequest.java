package com.example.crm_backend.network;

import com.example.crm_backend.models.Bill;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Bill request class
 * This class is used to parse a request to add a bill to the database
 */
public class BillRequest{
    private Employee employee;
    private Client client;
    private Bill bill;

    public BillRequest(Employee employee, Client client, Bill bill){
        this.employee = employee;
        this.client = client;
        this.bill = bill;
    }

    public Bill getBill(){
        return bill;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Client getClient() {
        return client;
    }
}