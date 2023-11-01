package com.example.crm_backend.repository;

import com.example.crm_backend.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public interface ClientRepository extends MongoRepository<Client, String> {
}
