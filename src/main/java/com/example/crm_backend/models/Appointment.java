package com.example.crm_backend.models;

import java.util.Date;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public class Appointment {
    private Date date;
    private String clientID;

    public Appointment() {
    }

    public Appointment(Date date, String clientID) {
        this.date = date;
        this.clientID = clientID;
    }
}
