package com.example.crm_backend.controllers;

import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Role;
import com.example.crm_backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Client controller
 */
@RestController
@RequestMapping("/api/client")
public class ClientController extends Controller{

    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Add an example client to the database
     * @return JSON with code 200 if the client was added, or code 500 if there was an error and a message
     */
    @PostMapping("/addExample")
    public String insertExample() {
        try {
            Client c = new Client("admin2", "admin2", "admin2", "admin2", "admin2", "admin2", Role.CLIENT, null);

            clientService.addClient(c);
        }
        catch (Exception e){
            return ret(500, "Error inserting client");
        }

        return ret(200, "Client inserted");
    }

    /**
     * Pin Pon
     * @return Pon
     */
    @GetMapping("/pin")
    public String pinPon() {
        return ret(200, "pon");
    }

    /**
     * Get the example client from the database
     * @return JSON with code 200 and the client if it was found, or code 500 and a message if there was an error
     */
    @GetMapping("/getExample")
    public String getExample(){
        Client c = null;
        try{
            c = clientService.getExampleClient();
        }
        catch (Exception e){
            return ret(500, "Error getting client");
        }

        return ret(200, c);
    }
}
