package com.example.crm_backend.models;

import com.example.crm_backend.network.config.DateJsonAdapter;
import com.google.gson.annotations.JsonAdapter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Bill class
 */
@Document(collection = "bills")
public class Bill {
    @Id
    private String id;

    private String name;

    @JsonAdapter(DateJsonAdapter.class)
    private Date emissionDate;

    @JsonAdapter(DateJsonAdapter.class)
    private Date expirationDate;

    //Se utiliza bigdecimal para evitar problemas con los decimales (es lo que se recomienda para el dinero)
    private BigDecimal price;

    private boolean paid;

    private String clientID;

    private String employeeID;

    public Bill() {
    }

    public Bill(Date emissionDate, Date expirationDate, BigDecimal price, boolean paid, String clientId, String employeeId, String name) {
        this.emissionDate = emissionDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.paid = paid;
        this.clientID = clientId;
        this.employeeID = employeeId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Date getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(Date emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", emisionDate=" + emissionDate +
                ", expirationDate=" + expirationDate +
                ", price=" + price +
                ", paid=" + paid +
                ", clientId='" + clientID + '\'' +
                ", employeeId='" + employeeID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
