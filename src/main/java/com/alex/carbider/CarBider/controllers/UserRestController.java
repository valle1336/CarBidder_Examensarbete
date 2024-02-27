package com.alex.carbider.CarBider.controllers;

import com.alex.carbider.CarBider.config.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final PasswordConfig passwordConfig;

    @Autowired
    public UserRestController(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }

    @GetMapping("/hash")
    public ResponseEntity<String> testBcryptHash(@RequestParam String password) {
        return new ResponseEntity<>(passwordConfig.bCryptPasswordEncoder().encode(password), HttpStatus.ACCEPTED);
    }



    @GetMapping("/helloAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> sayHelloToAdmin() {

        return new ResponseEntity<>("Hello ADMIN!", HttpStatus.ACCEPTED);
    }

    @GetMapping("/helloUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> sayHelloToUser() {

        return new ResponseEntity<>("Hello USER!", HttpStatus.ACCEPTED);
    }

    // TODO - Check if it works for WHOLE STRING! (ADMIN)
    @GetMapping("/sayGet")
    @PreAuthorize("hasAuthority('GET')")
    public ResponseEntity<String> checkGetAuthority() {

        return new ResponseEntity<>("You can only enter with GET Authority!", HttpStatus.ACCEPTED);
    }



}