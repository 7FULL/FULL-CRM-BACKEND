package com.example.crm_backend.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Employee class
 */
@Document(collection = "employees")
public class Employee extends User{

    @DBRef
    private Appointment[] appointments = new Appointment[0];

    @DBRef
    private Client[] clients = new Client[0];

    @DBRef
    private Bill[] bills = new Bill[0];

    public Employee() {
    }

    public Employee(String username, String password, String email, String name, String surname, String phone, Role role) {
        super(username, password, email, name, surname, phone, role);

        this.appointments = new Appointment[0];
        this.clients = new Client[0];
        this.bills = new Bill[0];
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

    public void addBill(Bill bill){
        Bill[] newBills = new Bill[bills.length + 1];
        for (int i = 0; i < bills.length; i++) {
            newBills[i] = bills[i];
        }
        newBills[bills.length] = bill;
        bills = newBills;
    }

    //Set all bills
    public void setBills(Bill[] bills) {
        this.bills = bills;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    public void setAppointments(Appointment[] appointments) {
        this.appointments = appointments;
    }

    public Bill[] getBills() {
        return bills;
    }

    public Client[] getClients() {
        return clients;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }
}
