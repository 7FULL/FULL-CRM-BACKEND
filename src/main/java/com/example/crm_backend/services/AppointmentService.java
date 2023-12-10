package com.example.crm_backend.services;

import com.example.crm_backend.controllers.MailManager;
import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Appointment service
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

    /**
     * Adds an appointment to the database and adds it to the employee and client
     * @param appointment   The appointment to add and save to the database
     * @param employee      The employee to add the appointment to
     * @param client        The client to add the appointment to
     */
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

    /**
     * Adds an appointment to the database
     * @param appointment   The appointment to save to the database
     */
    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    /**
     * Get all the appointments from the database
     * @return  An array with all the appointments
     */
    public Appointment[] getAppointments() {
        return appointmentRepository.findAllAppointments();
    }

    /**
     * Delete an appointment from the database
     * @param id    The id of the appointment to delete
     */
    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }
}
