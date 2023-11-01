package com.example.crm_backend.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Document(collection = "clients")
public class Client extends User{
    @DBRef
    private Appointment[] appointments;

    public Client() {
    }

    public Client(String username, String password, String email, String name, String surname, String phone, Role role, Appointment[] appointments) {
        super(username, password, email, name, surname, phone, role);

        if (appointments == null) {
            appointments = new Appointment[0];
        }

        this.appointments = appointments;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointment[] appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString(){
        String appointmentsString = "";
        for (Appointment appointment : appointments) {
            appointmentsString += appointment.toString();
        }

        return "Client{" +
                "appointments=" + appointmentsString +
                '}';
    }

    public void addAppointment(Appointment appointment) {
        Appointment[] newAppointments = new Appointment[appointments.length + 1];

        for (int i = 0; i < appointments.length; i++) {
            newAppointments[i] = appointments[i];
        }

        newAppointments[appointments.length] = appointment;

        appointments = newAppointments;
    }
}
