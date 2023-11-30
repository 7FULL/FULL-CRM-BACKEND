package com.example.crm_backend.services;

import com.example.crm_backend.controllers.MailManager;
import com.example.crm_backend.models.Bill;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.repository.BillRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Bill service
 */
@Service
public class BillService {

    private EmployeeService employeeService;

    private ClientService clientService;

    private BillRespository billRespository;

    private MailManager mailManager;

    @Autowired
    public BillService(EmployeeService employeeService, ClientService clientService,
                       BillRespository billRespository, MailManager mailManager) {
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.billRespository = billRespository;
        this.mailManager = mailManager;

        employeeService.setBillService(this);
    }

    /**
     * Add a bill to the database and link it to the employee and client that made it.
     * Also sends an email to both of them and saves the bill in their respective lists
     * @param bill      Bill to add
     * @param employee  Employee that made the bill
     * @param client    Client to whom the bill is addressed
     */
    public void addBill(Bill bill, Employee employee, Client client){
        bill = billRespository.save(bill);

        if(employee != null && employee.getId() != null){
            employee = employeeService.addBill(employee, bill);
            bill.setEmployeeID(employee.getId());
            mailManager.sendEmail(employee.getEmail(), employee.getName(),"Nueva factura",
                    "Se ha creado una factura con el cliente " + client.getName() +
                            " con el concepto de pago de " + bill.getName() +
                            " por un valor de " + bill.getPrice() + "€");
        }

        if(client != null && client.getId() != null){
            client = clientService.addBill(client, bill);
            bill.setClientID(client.getId());
            //Le mandamos un correo al cliente y al empleado para que sepan que tienen una nueva factura
            mailManager.sendEmail(client.getEmail(), client.getName(), "Nueva factura",
                    "Se ha creado una factura con el empleado " + employee.getName() +
                            " con el concepto de pago de " + bill.getName() +
                            " por un valor de " + bill.getPrice() + "€");
        }

        billRespository.save(bill);
    }

    /**
     * Get all the bills from the database
     * @return  Array of bills
     */
    public Bill[] getBills() {
        return billRespository.findAllBills();
    }
}
