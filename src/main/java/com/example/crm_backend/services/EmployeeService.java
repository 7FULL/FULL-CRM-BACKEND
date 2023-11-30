package com.example.crm_backend.services;

import com.example.crm_backend.controllers.MailManager;
import com.example.crm_backend.models.*;
import com.example.crm_backend.repository.EmployeeRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Employee service
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

    /**
     * @param billService Set the bill service.
     */
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    /**
     * Check if someone can login with the given username and password.
     * If the username is not found, we search by email.
     * <p>
     * If the user is an admin, we add all the employees.
     * If the user is a manager or an admin,
     * we add all the employees as clients and all the bills as their bills for them to being able to see them.
     * <p>
     * If the password is correct, we send an email to the user to notify him that someone has logged in their account.
     * @param username  The username or email of the user.
     * @param password  The password of the user.
     * @return          The employee if the login was successful, null otherwise.
     */
    public Employee login(String username, String password) {
        //En caso de que no exista por usuario buscamos por email
        Employee e = employeeRepository.findByUsername(username);

        if (e == null) {
            e = employeeRepository.findByEmail(username);
        }

        if (e == null) {
            return null;
        }

        if (e.getPassword().equals(DigestUtils.md5Hex(password))) {
            //En caso de que sea el administrador añadimos todos los empleados que existen como clientes
            //y todas las facturas que existen como facturaschrome

            if (e.getRole() == Role.ADMIN || e.getRole() == Role.MANAGER) {
                e.setClients(convertToClients(employeeRepository.findAllEmployees()));
                e.setBills(billService.getBills());
            }

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

    /**
     * Convert an array of employees to an array of clients. This is used to add all the employees as clients to the admin.
     * @param employees The array of employees.
     * @return          The array of clients.
     */
    private Client[] convertToClients(Employee[] employees) {
        Client[] clients = new Client[employees.length];

        for (int i = 0; i < employees.length; i++) {
            clients[i] = new Client(employees[i]);
        }

        return clients;
    }

    /**
     * Add an appointment to an employee.
     * @param employee      The employee.
     * @param appointment   The appointment.
     * @return              The employee with the appointment added.
     */
    public Employee addAppointment(Employee employee, Appointment appointment) {
        //Employee e = employeeRepository.findById(employeeId).get();
        employee.addAppointment(appointment);

        employeeRepository.save(employee);

        return employee;
    }

    /**
     * Add an employee to the database.
     * @param e The employee.
     */
    public void addEmployee(Employee e) {
        employeeRepository.save(e);
    }

    /**
     * Get the employee with the given id.
     * @param employeeId    The id of the employee.
     * @return              The employee if it exists, null otherwise.
     */
    public Employee getEmployee(String employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    /**
     * Delete the employee with the given id.
     * @param employeeId    The id of the employee.
     */
    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Get the example employee from the database.
     * @return  The example employee if it exists, null otherwise.
     */
    public Employee getExampleEmployee() {
        Employee e = employeeRepository.findByUsername("admin");

        return e;
    }

    /**
     * Get the employee with the given username.
     * @param username  The username of the employee.
     * @return          The employee if it exists, null otherwise.
     */
    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    /**
     * Get the employee with the given id.
     * @param id    The id of the employee.
     * @return      The employee if it exists, null otherwise.
     */
    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id).get();
    }

    /**
     * Add a bill to an employee.
     * @param employee  The employee.
     * @param bill      The bill.
     * @return          The employee with the bill added.
     */
    public Employee addBill(Employee employee, Bill bill) {
        //Employee e = employeeRepository.findById(employeeId).get();
        employee.addBill(bill);

        employeeRepository.save(employee);

        return employee;
    }

    /**
     * Get the employee with the given email.
     * @param email The email of the employee.
     * @return      The employee if it exists, null otherwise.
     */
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    /**
     * Add a client to an employee.
     * @param employee  The employee.
     * @param client    The client.
     * @return          The employee with the client added.
     */
    public Employee addClient(Employee employee, Client client){
        employee.addClient(client);

        employeeRepository.save(employee);

        return employee;
    }

    /**
     * Generate a token for an employee and send it to their email.
     * @param e The employee associated with the token generated and the email to send it to.
     */
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

    /**
     * Generate the html for the email to send to the employee with the token.
     * @param token The token to send.
     * @return      The html for the email.
     */
    public String tokenHtml(String token){
        return "<h1>Recuperación de contraseña</h1><p>Para recuperar su contraseña, introduzca el siguiente token en la aplicación: <b>" + token + "</b></p>";
    }

    /**
     * Change the password of an employee.
     * @param e         The employee.
     * @param password  The new password.
     */
    public void changePassword(Employee e, String password){
        e.setPassword(password);

        //Enciamos un correo para avisar de que se ha cambiado la contraseña
        mailManager.sendEmail(e.getEmail(), e.getUsername(), "Contraseña cambiada",
                "Se ha cambiado la contraseña de su cuenta, " +
                        "si no ha sido usted, cambie su contraseña inmediatamente");

        employeeRepository.save(e);
    }

    /**
     * Get the employee with the given token.
     * @param token The token of the employee.
     * @return      The employee if it exists, null otherwise.
     */
    public Employee getEmployeeByToken(String token){
        return employeeRepository.findByToken(token);
    }

    /**
     * Get all the employees from the database.
     * @return  The array of employees.
     */
    public Employee[] getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }
}
