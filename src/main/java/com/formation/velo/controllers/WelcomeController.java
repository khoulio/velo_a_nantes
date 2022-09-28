package com.formation.velo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller    // This means that this class is a Controller
@RequestMapping(path = "/api")
public class WelcomeController {

    @GetMapping("/welcome")
    public ResponseEntity<String>  welcome(){
        return  ResponseEntity.ok("Welcome");
    }
}
