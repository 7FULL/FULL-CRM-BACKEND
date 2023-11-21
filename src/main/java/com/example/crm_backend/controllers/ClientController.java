package com.example.crm_backend.controllers;

import com.example.crm_backend.models.Client;
import com.example.crm_backend.models.Role;
import com.example.crm_backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@RestController
@RequestMapping("/api/client")
public class ClientController extends Controller{

    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/addExample")
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

    @GetMapping("/pin")
    public String pinPon() {
        return ret(200, "pon");
    }

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
