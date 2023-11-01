package com.example.crm_backend.services;

import com.example.crm_backend.models.Appointment;
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
        //TODO: Preguntar al profe si la logica de errores se hace en el servicio o en el controlador
        return employeeRepository.findEmployeeByUsernameAndPassword(username, password);
    }

    public Employee addAppointment(String employeeId, Appointment appointment) {

        Employee e = employeeRepository.findById(employeeId).get();

        e.addAppointment(appointment);

        employeeRepository.save(e);

        return e;
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
}
