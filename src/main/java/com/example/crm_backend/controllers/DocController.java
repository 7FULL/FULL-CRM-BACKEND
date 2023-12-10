package com.example.crm_backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the documentation
 */
@Controller
public class DocController {

    /**
     * Redirect to the documentation
     * @return The index.html of the javaDoc
     */
    @GetMapping("/doc")
    public String doc() {
        return "redirect:https://7full.000webhostapp.com/";
    }

    /**
     * Redirect to the index
     * @return A html saying welcome
     */
    @GetMapping("/")
    public String index() {
        return "hola";
    }
}
