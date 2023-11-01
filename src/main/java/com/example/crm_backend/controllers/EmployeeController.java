package com.example.crm_backend.controllers;

/*
 *
 *@author Pablo Hermida Gómez DAM G2
 *
 */

import com.example.crm_backend.Data;
import com.example.crm_backend.models.Employee;
import com.example.crm_backend.models.Role;
import com.example.crm_backend.services.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController extends Controller{
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        Employee e = employeeService.login(username, password);

        if (e == null) {
            return ret(401, new Employee());
        }

        return ret(200, e);
    }

    @GetMapping("/addExample")
    public String addExample() {
        Employee e = new Employee("admin", "admin", "admin", "admin", "admin", "admin", Role.EMPLOYEE);

        employeeService.addEmployee(e);

        return ret(200, "Employee inserted");
    }

    @GetMapping("/pin")
    public String pinPon() {
        return ret(200, "pon");
    }

    //This function is used to get the employee data (admin)
    @GetMapping("/getExample")
    public String getExample() {
        Employee e = employeeService.getExampleEmployee();

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
