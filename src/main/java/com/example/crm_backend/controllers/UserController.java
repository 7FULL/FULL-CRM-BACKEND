package com.example.crm_backend.controllers;

/*
 *
 *@author Pablo Hermida Gómez DAM G2
 *
 */

import com.example.crm_backend.Data;
import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.repository.EmployeeRepository;
import com.example.crm_backend.models.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    EmployeeRepository employeeRepository;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private String ret(int code, String data){

        System.out.println("code: " + code + " data: " + data);

        return gson.toJson(new Data(code, data));
    }

    private String ret(int code, Object object){
        Data data = new Data(code, object);

        System.out.println("code: " + code + " data: " + data.toString());

        return gson.toJson(data);
    }

    private String ret(Data data){

        System.out.println("code: " + data.getCode() + " data: " + data);

        return gson.toJson(data);
    }

    @GetMapping("/api/pin")
    public String pin() {
        return ret(200, "pon");
    }

    @PostMapping("/api/employee/login")
    public String login(String username, String password) {
        Employee e = employeeRepository.findEmployeeByUsernameAndPassword(username, password);

        if (e == null) {
            return ret(401, new Employee());
        }

        return ret(200, e);
    }

    @GetMapping("/api/devEmployee")
    public String devUser(){
        Employee e = new Employee("653ab8295aaff87c4eddcb44","admin", "admin", "admin", "admin", "ada", "a", new Appointment[0]);

        return ret(200, e);
    }

    @GetMapping("api/employee/insertRandom")
    public String insertRandom(){
        Employee e = new Employee("admin", "admin", "admin", "admin", "ada", "a", new Appointment[]{new Appointment(new Date(), "clientID")});

        employeeRepository.save(e);

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
