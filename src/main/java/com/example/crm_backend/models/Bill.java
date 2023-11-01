package com.example.crm_backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Document(collection = "bills")
public class Bill {
    @Id
    private String id;

    private Date emisionDate;
    private Date expirationDate;

    //Se utiliza bigdecimal para evitar problemas con los decimales (es lo que se recomienda para el dinero)
    private BigDecimal price;

    private boolean paid;
}
