package com.example.crm_backend.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Document(collection = "employees")
public class Employee extends User{

    @DBRef
    private Appointment[] appointments;

    @DBRef
    private Client[] clients;

    public Employee() {
    }

    public Employee(String username, String password, String email, String name, String surname, String phone, Role role, Appointment[] appointments, Client[] clients) {
        super(username, password, email, name, surname, phone, role);

        if (appointments == null) {
            appointments = new Appointment[0];
        }

        if (clients == null) {
            clients = new Client[0];
        }

        this.appointments = appointments;
        this.clients = clients;
    }

    public Employee(String username, String password, String email, String name, String surname, String phone, Role role) {
        super(username, password, email, name, surname, phone, role);

        this.appointments = new Appointment[0];
        this.clients = new Client[0];
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public void addAppointment(Appointment appointment){
        Appointment[] newAppointments = new Appointment[appointments.length + 1];
        for (int i = 0; i < appointments.length; i++) {
            newAppointments[i] = appointments[i];
        }
        newAppointments[appointments.length] = appointment;
        appointments = newAppointments;
    }

    public void addClient(Client client){
        Client[] newClients = new Client[clients.length + 1];
        for (int i = 0; i < clients.length; i++) {
            newClients[i] = clients[i];
        }
        newClients[clients.length] = client;
        clients = newClients;
    }
}
