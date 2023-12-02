package com.example.crm_backend.controllers;

/**
 *
 *@author Pablo Hermida Gómez DAM G2
 *
 */

import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.models.Role;
import com.example.crm_backend.services.ClientService;
import com.example.crm_backend.services.EmployeeService;
import io.sentry.Sentry;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Employee controller
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController extends Controller{
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * This method allows you to login with a username or email and a password
     * @param username  Username or email to login with
     * @param password  Password to login with
     * @return          JSON with code 200 and the employee if the login was successful, or code 401 and a message if it wasn't
     */
    @PostMapping("/login")
    public String login(String username, String password) {
        Employee em = null;
        try {
            em = employeeService.login(username, password);

            if (em == null){
                System.out.println("Error logging in");

                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                return ret(401, new Employee());
            }
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ret(500, "Error logging in");
        }

        return ret(200, em);
    }

    /**
     *Add an example client to the database
     * @return
     */
    @PostMapping("/addExample")
    public String addExample() {
        try{
            Employee e = new Employee("admin", "admin", "admin", "admin", "admin", "admin", Role.EMPLOYEE);

            employeeService.addEmployee(e);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ret(500, "Error inserting employee");
        }

        return ret(200, "Employee inserted");
    }

    /**
     * Pin Pon
     * @return Pon
     */
    @GetMapping("/pin")
    public String pinPon() {
        return ret(200, "pon");
    }

    /**
     * Get the example client from the database
     * @return JSON with code 200 and the employee if it was found, or code 500 and a message if there was an error
     */
    @GetMapping("/getExample")
    public String getExample() {
        Employee em = null;
        try {
            em = employeeService.getExampleEmployee();
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ret(500, "Error getting employee");
        }

        return ret(200, em);
    }

    /**
     * Add a client to an employee
     * @param employee  Employee to add the client to
     * @param client    Client to add to the employee
     * @return          JSON with code 200 if the client was added, or code 500 if there was an error and a message
     */
    @PostMapping("/addClient")
    public String addClient(Employee employee, Client client){
        try {
            employeeService.addClient(employee, client);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ret(500, "Error adding client");
        }

        return ret(200, "Client added");
    }

    /**
     * This method is called when a client forgets his password
     * @param email Email of the client
     * @return    JSON with code 200 if the email was sent, or code 500 if there was an error and a message
     */
    @PostMapping("/forgotPassword")
    public String forgotPassword(String email){
        try {
            Employee e = employeeService.getEmployeeByEmail(email);

            if(e == null){
                return ret(404, "Employee not found");
            }

            employeeService.generateToken(e);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ret(500, "Error sending email");
        }

        return ret(200, "Email sent");
    }

    /**
     * This method is called when a client wants to change his password. It checks if the token is correct and then changes the password
     * @param token     Token to check
     * @param password  New password
     * @return          JSON with code 200 if the password was changed, or code 500 if there was an error and a message
     */
    @PutMapping("/newPassword")
    public String newPassword(String token, String password){
        try{
            Employee e = employeeService.getEmployeeByToken(token);

            if(e == null){
                return ret(404, "Employee not found");
            }

            password = DigestUtils.md5Hex(password);

            employeeService.changePassword(e, password);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ret(500, "Error changing password");
        }

        return ret(200, "Password changed");
    }

    /**
     * This method is called when a client wants to change his password. It checks if the token is correct and then changes the password
     * @param token     Token to check
     * @return          JSON with code 200 if the token is correct, or code 500 if there was an error and a message
     */
    @PostMapping("/checkToken")
    public String checkToken(String token){
        try {
            Employee e = employeeService.getEmployeeByToken(token);

            if(e == null){
                return ret(404, "Employee not found");
            }
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ret(500, "Error checking token");
        }

        return ret(200, "Token correct");
    }

    /**
     * Get the employee with the given username
     * @param username  Username of the employee
     * @return          JSON with code 200 and the employee if it was found, or code 500 and a message if there was an error
     */
    @PostMapping("/getEmployee")
    public String getEmployee(String username){
        Employee e = null;
        try {
            e = employeeService.getEmployeeByUsername(username);

            if(e == null){
                return ret(404, "Employee not found");
            }
        }
        catch (Exception ex){
            Sentry.captureException(ex);
            return ret(500, "Error getting employee");
        }

        return ret(200, e);
    }
}

//region HTTP codes
// 200 OK
// 201 Created (Creado)
// 202 Accepted (Aceptado)
// 204 No Content (Sin contenido)
// 301 Moved Permanently (Movido permanentemente)
// 302 Found (Encontrado)
// 304 Not Modified (No modificado)
// 400 Bad Request (Petición incorrecta)
// 401 Unauthorized (No autorizado)
// 403 Forbidden (Prohibido)
// 404 Not Found (No encontrado)
// 500 Internal Server Error (Error interno del servidor)
// 503 Service Unavailable (Servicio no disponible)
// 504 Gateway Timeout (Tiempo de espera agotado)
//endregion
