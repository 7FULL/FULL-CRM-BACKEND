package com.example.crm_backend.controllers;

import com.example.crm_backend.network.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public abstract class Controller {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String ret(int code, String data){
        System.out.println("*********************************");
        System.out.println();
        System.out.println("code: " + code + " data: " + data);
        System.out.println();
        System.out.println("*********************************");

        String ret = gson.toJson(new Data(code, data));

        if (code == 200) {
            Logger.log(data);
        } else {
            Logger.logError(data);
        }

        return ret;
    }

    public String ret(int code, Object object){
        Data data = new Data(code, object);

        System.out.println("*********************************");
        System.out.println();
        System.out.println("code: " + code + " data: " + data.toString());
        System.out.println();
        System.out.println("*********************************");

        String ret = gson.toJson(data);

        if (code == 200) {
            Logger.log(ret);
        } else {
            Logger.logError(ret);
        }

        return ret;
    }
}
