package com.example.crm_backend.controllers;

import com.example.crm_backend.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public abstract class Controller {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String ret(int code, String data){

        System.out.println("code: " + code + " data: " + data);

        return gson.toJson(new Data(code, data));
    }

    public String ret(int code, Object object){
        Data data = new Data(code, object);

        System.out.println("code: " + code + " data: " + data.toString());

        return gson.toJson(data);
    }

    public String ret(Data data){

        System.out.println("code: " + data.getCode() + " data: " + data);

        return gson.toJson(data);
    }
}
