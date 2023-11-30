package com.example.crm_backend.models;

import com.example.crm_backend.network.config.DateJsonAdapter;
import com.google.gson.annotations.JsonAdapter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Appointment class
 */
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    @JsonAdapter(DateJsonAdapter.class)
    private Date date;

    private String description;

    private String clientID;

    private String employeeID;

    public Appointment() {
    }

    public Appointment(Date date, String description, String client, String employee) {
        this.date = date;
        this.description = description;
        this.clientID = client;
        this.employeeID = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient() {
        return clientID;
    }

    public void setClient(String client) {
        this.clientID = client;
    }

    public String getEmployee() {
        return employeeID;
    }

    public void setEmployee(String employee) {
        this.employeeID = employee;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Appointment{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", client=" + clientID +
                ", employee=" + employeeID +
                '}';
    }
}
