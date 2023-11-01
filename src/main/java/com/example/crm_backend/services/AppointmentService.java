package com.example.crm_backend.services;

import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    private EmployeeService employeeService;

    private ClientService clientService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, EmployeeService employeeService, ClientService clientService) {
        this.appointmentRepository = appointmentRepository;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    public Appointment getAppointment(String appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    public void addAppointment(Appointment appointment, String employeeId, String clientId) {
        //We do this because we need the id of the appointment to add it to the employee and client
        //And the id is generated when we save it
        appointment = appointmentRepository.save(appointment);

        System.out.println(appointment.getId());

        Employee employee = employeeService.addAppointment(employeeId, appointment);

        Client client = clientService.addAppointment(clientId, appointment);

        appointment.setEmployee(employee.getId());
        appointment.setClient(client.getId());

        appointmentRepository.save(appointment);
    }

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
}
