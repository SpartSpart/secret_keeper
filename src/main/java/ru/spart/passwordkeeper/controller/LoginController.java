package ru.spart.passwordkeeper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class LoginController {


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/login")
    public ResponseEntity<Void> loginUser() {
        return ResponseEntity.ok().build();

    }

}
