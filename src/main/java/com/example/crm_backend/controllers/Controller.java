package com.example.crm_backend.controllers;

import com.example.crm_backend.network.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public abstract class Controller {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Returns a json string containing the code and the object passed as parameters. If the code passed is not 200 it will be logged as an error.
     * @param code      HTTP code
     * @param object    Object to be converted to json
     * @return          Json string
     */
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
