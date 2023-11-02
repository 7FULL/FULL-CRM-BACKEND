package com.example.crm_backend.repository;

import com.example.crm_backend.models.Appointment;
import com.example.crm_backend.models.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public interface BillRespository extends MongoRepository<Bill, String> {
}
