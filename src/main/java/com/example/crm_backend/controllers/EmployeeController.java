package com.example.crm_backend.controllers;

/*
 *
 *@author Pablo Hermida Gómez DAM G2
 *
 */

import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.models.Role;
import com.example.crm_backend.services.ClientService;
import com.example.crm_backend.services.EmployeeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController extends Controller{
    private EmployeeService employeeService;

    ClientService clientService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ClientService clientService) {
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        Employee em = null;
        try {
            em = employeeService.login(username, password);

            if (em == null){
                System.out.println(username + " " + password);
                return ret(401, new Employee());
            }
        }
        catch (Exception e){
            return ret(500, "Error logging in");
        }

        return ret(200, em);
    }

    @GetMapping("/addExample")
    public String addExample() {
        try{
            Employee e = new Employee("admin", "admin", "admin", "admin", "admin", "admin", Role.EMPLOYEE);

            employeeService.addEmployee(e);
        }
        catch (Exception e){
            return ret(500, "Error inserting employee");
        }

        return ret(200, "Employee inserted");
    }

    @GetMapping("/pin")
    public String pinPon() {
        return ret(200, "pon");
    }

    @GetMapping("/getExample")
    public String getExample() {
        Employee em = null;
        try {
            em = employeeService.getExampleEmployee();
        }
        catch (Exception e){
            return ret(500, "Error getting employee");
        }

        return ret(200, em);
    }

    @PostMapping("/addClient")
    public String addClient(Employee employee, Client client){
        try {
            employeeService.addClient(employee, client);
        }
        catch (Exception e){
            return ret(500, "Error adding client");
        }

        return ret(200, "Client added");
    }

    @PostMapping("/addExampleClient")
    public String addExampleClient(){
        try {
            Client c = clientService.getExampleClient();

            employeeService.addClient(employeeService.getExampleEmployee(), c);
        }
        catch (Exception e){
            return ret(500, "Error adding client");
        }

        return ret(200, "Client added");
    }

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
            return ret(500, "Error sending email");
        }

        return ret(200, "Email sent");
    }

    @PostMapping("/newPassword")
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
            return ret(500, "Error changing password");
        }

        return ret(200, "Password changed");
    }

    @PostMapping("/checkToken")
    public String checkToken(String token){
        try {
            Employee e = employeeService.getEmployeeByToken(token);

            if(e == null){
                return ret(404, "Employee not found");
            }
        }
        catch (Exception e){
            return ret(500, "Error checking token");
        }

        return ret(200, "Token correct");
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
