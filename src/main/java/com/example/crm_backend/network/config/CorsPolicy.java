package com.example.crm_backend.network.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */

/**
 * This class is used to configure the CORS policy
 */
@Configuration
public class CorsPolicy implements WebMvcConfigurer {
    //Hacemos esto para que no nos de problemas con el CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }
}
