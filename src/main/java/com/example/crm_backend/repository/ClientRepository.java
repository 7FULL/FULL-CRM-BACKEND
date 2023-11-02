package com.example.crm_backend.repository;

import com.example.crm_backend.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public interface ClientRepository extends MongoRepository<Client, String> {

    @Query("{ 'username' : ?0 }")
    Client findByUsername(String username);
}
