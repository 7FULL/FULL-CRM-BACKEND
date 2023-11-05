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

    @DBRef
    private Bill[] bills;

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
        String s = super.toString();

        if(appointments != null && appointments.length > 0) {
            s += "Appointments: \n";
            for (Appointment appointment : appointments) {
                s += appointment.toString() + "\n";
            }
        }

        if(bills != null && bills.length > 0) {
            s += "Bills: \n";
            for (Bill bill : bills) {
                s += bill.toString() + "\n";
            }
        }

        return s;
    }

    public void addAppointment(Appointment appointment) {
        if (appointments == null){
            appointments = new Appointment[]{appointment};
            return;
        }

        Appointment[] newAppointments = new Appointment[appointments.length + 1];

        for (int i = 0; i < appointments.length; i++) {
            newAppointments[i] = appointments[i];
        }

        newAppointments[appointments.length] = appointment;

        appointments = newAppointments;
    }

    public void addBill(Bill bill) {
        if (bills == null){
            bills = new Bill[]{bill};
            return;
        }

        Bill[] newBills = new Bill[bills.length + 1];

        for (int i = 0; i < bills.length; i++) {
            newBills[i] = bills[i];
        }

        newBills[bills.length] = bill;

        bills = newBills;
    }
}
