package com.example.crm_backend.services;

import com.example.crm_backend.controllers.MailManager;
import com.example.crm_backend.models.*;
import com.example.crm_backend.repository.EmployeeRepository;
import org.apache.commons.codec.digest.DigestUtils;
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

    private BillService billService;

    private MailManager mailManager;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, MailManager mailManager) {
        this.employeeRepository = employeeRepository;
        this.mailManager = mailManager;
    }

    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    public Employee login(String username, String password) {
        //En caso de que no exista por usuario buscamos por email
        Employee e = employeeRepository.findByUsername(username);

        if (e == null) {
            e = employeeRepository.findByEmail(username);
        }

        if (e == null) {
            return null;
        }

        //En caso de que sea el administrador añadimos todos los empleados que existen como clientes
        //y todas las facturas que existen como facturaschrome

        if (e.getRole() == Role.ADMIN || e.getRole() == Role.MANAGER) {
            e.setClients(convertToClients(employeeRepository.findAllEmployees()));
            e.setBills(billService.getBills());
        }

        if (e.getPassword().equals(DigestUtils.md5Hex(password))) {
            //Avisar por correo de que ha habido un inicio de sesion
            mailManager.sendEmail(e.getEmail(), e.getUsername(),
                    "Inicio de sesión",
                    "Se ha iniciado sesión en su cuenta, " +
                            "si no ha sido usted, cambie su contraseña inmediatamente");

            return e;
        }

        //Avisar por correo de que ha habido un intento de inicio de sesion fallido
        mailManager.sendEmail(e.getEmail(), e.getUsername(),
                "Intento de inicio de sesión fallido",
                "Se ha intentado iniciar sesión en su cuenta desde una ubicación desconocida, " +
                        "si no ha sido usted, cambie su contraseña inmediatamente");

        return null;
    }

    private Client[] convertToClients(Employee[] employees) {
        Client[] clients = new Client[employees.length];

        for (int i = 0; i < employees.length; i++) {
            clients[i] = new Client(employees[i]);
        }

        return clients;
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

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public void addClient(Employee employee, Client client){
        employee.addClient(client);

        employeeRepository.save(employee);
    }

    public void generateToken(Employee e){
        String token =  "";

        //Numeros y letras aleatorias
        for (int i = 0; i < 7; i++) {
            token += (char) (Math.random() * 26 + 'a');
        }

        e.setToken(token);

        employeeRepository.save(e);

        mailManager.sendEmail(e.getEmail(), e.getUsername(), "Recuperación de contraseña", tokenHtml(token));
    }

    public String tokenHtml(String token){
        return "<h1>Recuperación de contraseña</h1><p>Para recuperar su contraseña, introduzca el siguiente token en la aplicación: <b>" + token + "</b></p>";
    }

    public void changePassword(Employee e, String password){
        e.setPassword(password);

        //Enciamos un correo para avisar de que se ha cambiado la contraseña
        mailManager.sendEmail(e.getEmail(), e.getUsername(), "Contraseña cambiada",
                "Se ha cambiado la contraseña de su cuenta, " +
                        "si no ha sido usted, cambie su contraseña inmediatamente");

        employeeRepository.save(e);
    }

    public Employee getEmployeeByToken(String token){
        return employeeRepository.findByToken(token);
    }
}
