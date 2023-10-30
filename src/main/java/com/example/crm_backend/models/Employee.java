package com.example.crm_backend.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;

    private Appointment[] appointments;

    public Employee() {
    }

    public Employee(String id, String username, String password, String name, String surname, String email, String phone, Appointment[] appointments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.appointments = appointments;
    }

    public Employee(String username, String password, String name, String surname, String email, String phone, Appointment[] appointments) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.appointments = appointments;
    }

    //region getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //endregion

    @Override
    public String toString() {
        return String.format(
                "Employee[id=%s, username='%s', password='%s', name='%s', surname='%s', email='%s', phone='%s']",
                id, username, password, name, surname, email, phone);
    }
}
