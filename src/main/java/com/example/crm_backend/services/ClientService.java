package com.example.crm_backend.services;

import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Bill;
import com.example.crm_backend.models.Client;
import com.example.crm_backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Client service
 */
@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Add a new client to the database
     * @param client    The client to add
     */
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    /**
     * Add an appointment to a client
     * @param client        The client to add the appointment
     * @param appointment   The appointment to add
     * @return              The client with the new appointment
     */
    public Client addAppointment(Client client, Appointment appointment) {
        //Client c = clientRepository.findById(clientId).get();

        client.addAppointment(appointment);

        clientRepository.save(client);

        return client;
    }

    /**
     * Add a bill to a client
     * @param client    The client to add the bill
     * @param bill      The bill to add
     * @return          The client with the new bill
     */
    public Client addBill(Client client, Bill bill) {
        //Client c = clientRepository.findById(clientId).get();
        //Comprobasmos si la factura ya existe y si existe no la añadimos
        for (Bill b : client.getBills()) {
            if (b.getId().equals(bill.getId())) {
                return client;
            }
        }

        clientRepository.save(client);

        return client;
    }

    /**
     * Get a client by its id
     * @param clientId  The id of the client
     * @return          The client, if exists in the database or null if not
     */
    public Client getClient(String clientId) {
        return clientRepository.findById(clientId).get();
    }

    /**
     * Delete a client by its id
     * @param clientId  The id of the client
     */
    public void deleteClient(String clientId) {
        clientRepository.deleteById(clientId);
    }

    /**
     * Get the example client from the database
     * @return  The example client from the database or null if not exists
     */
    public Client getExampleClient() {
        return clientRepository.findByUsername("admin2");
    }

    /**
     * Get all the clients from the database
     * @return  An array with all the clients
     */
    public Client[] getClients() {
        return clientRepository.findAllClients();
    }
}
