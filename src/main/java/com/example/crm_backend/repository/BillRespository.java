package com.example.crm_backend.repository;

import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Bill repository
 */
public interface BillRespository extends MongoRepository<Bill, String> {

    @Query("{ }")
    Bill[] findAllBills();
}
