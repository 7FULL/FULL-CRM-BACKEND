package com.example.crm_backend.services;

import com.example.crm_backend.controllers.MailManager;
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

    private MailManager mailManager;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              EmployeeService employeeService, ClientService clientService,
                              MailManager mailManager) {
        this.appointmentRepository = appointmentRepository;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.mailManager = mailManager;
    }

    public void addAppointment(Appointment appointment, Employee employee, Client client) {
        //We do this because we need the id of the appointment to add it to the employee and client
        //And the id is generated when we save it
        appointment = appointmentRepository.save(appointment);

        employee = employeeService.addAppointment(employee, appointment);

        client = clientService.addAppointment(client, appointment);

        //Le mandamos un correo al cliente y al empleado para que sepan que tienen una cita
        mailManager.sendEmail(client.getEmail(), client.getName(), "Cita creada", "Se ha creado una cita con el empleado " + employee.getName() + " para el día " + appointment.getDate());
        mailManager.sendEmail(employee.getEmail(), employee.getName(),"Cita creada", "Se ha creado una cita con el cliente " + client.getName() + " para el día " + appointment.getDate());

        appointment.setEmployee(employee.getId());
        appointment.setClient(client.getId());

        appointmentRepository.save(appointment);
    }

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }
}
