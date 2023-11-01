package com.example.crm_backend.services;

import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public Client addAppointment(String clientId, Appointment appointment) {
        Client c = clientRepository.findById(clientId).get();

        c.addAppointment(appointment);

        clientRepository.save(c);

        return c;
    }


}
