package com.example.crm_backend.services;

import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Bill;
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

    public Client addAppointment(Client client, Appointment appointment) {
        //Client c = clientRepository.findById(clientId).get();

        client.addAppointment(appointment);

        clientRepository.save(client);

        return client;
    }

    public Client addBill(Client client, Bill bill) {
        //Client c = clientRepository.findById(clientId).get();

        client.addBill(bill);

        clientRepository.save(client);

        return client;
    }

    public Client getClient(String clientId) {
        return clientRepository.findById(clientId).get();
    }

    public void deleteClient(String clientId) {
        clientRepository.deleteById(clientId);
    }

    public Client getExampleClient() {
        Client c = clientRepository.findByUsername("admin2");

        return c;
    }
}
