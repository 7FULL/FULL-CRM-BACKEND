package com.example.crm_backend;

import com.example.crm_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication()
public class CrmBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmBackendApplication.class, args);
    }
}
