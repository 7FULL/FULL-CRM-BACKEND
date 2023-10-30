package com.example.crm_backend;

import com.example.crm_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrmBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmBackendApplication.class, args);
    }
}
