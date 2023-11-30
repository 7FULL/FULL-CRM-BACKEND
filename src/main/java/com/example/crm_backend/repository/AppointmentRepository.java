package com.example.crm_backend.repository;

import com.example.crm_backend.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * Appointment repository
 */
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    @Query("{ }")
    Appointment[] findAllAppointments();
}
