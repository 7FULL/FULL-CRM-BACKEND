package com.example.crm_backend.services;

import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Bill;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee login(String username, String password) {
        return employeeRepository.findEmployeeByUsernameAndPassword(username, password);
    }

    public Employee addAppointment(Employee employee, Appointment appointment) {
        //Employee e = employeeRepository.findById(employeeId).get();
        employee.addAppointment(appointment);

        employeeRepository.save(employee);

        return employee;
    }

    public void addEmployee(Employee e) {
        employeeRepository.save(e);
    }

    public Employee getEmployee(String employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public Employee getExampleEmployee() {
        Employee e = employeeRepository.findByUsername("admin");

        return e;
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id).get();
    }

    public Employee addBill(Employee employee, Bill bill) {
        //Employee e = employeeRepository.findById(employeeId).get();
        employee.addBill(bill);

        employeeRepository.save(employee);

        return employee;
    }

    public void addClient(Employee employee, Client client){
        employee.addClient(client);

        employeeRepository.save(employee);
    }
}
