package com.example.crm_backend.repository;

import com.example.crm_backend.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query("{ 'username' : ?0 }")
    Employee findByUsername(String username);

    @Query("{ 'email' : ?0 }")
    Employee findByEmail(String email);

    @Query("{ 'username' : ?0, 'password' : ?1 }")
    Employee findEmployeeByUsernameAndPassword(String username, String password);

    @Query("{ 'token' : ?0 }")
    Employee findByToken(String token);
}
