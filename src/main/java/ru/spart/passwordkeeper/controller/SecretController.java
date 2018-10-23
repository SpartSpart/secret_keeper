package ru.spart.passwordkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spart.passwordkeeper.controller.model.Secret;
import ru.spart.passwordkeeper.service.SecretService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SecretController {

    private final SecretService secretService;


    @Autowired
    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping(value = "/secrets")
    public ResponseEntity<Void> addSecret(@RequestBody Secret secret) {
        secretService.add(secret);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/secrets/{id}")
    public ResponseEntity<Void> updateSecret(@PathVariable("id") long id, @RequestBody Secret secret) {
        secretService.update(id, secret);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/secrets/{id}")
    public ResponseEntity<Secret> getSecret(@PathVariable("id") long id) {
        return ResponseEntity
                .ok()
                .body(secretService.getSecret(id));
    }

    @DeleteMapping(value = "/secrets/{id}")
    public ResponseEntity<Void> deleteSecret(@PathVariable("id") long id) {
        secretService.deleteSecret(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/secrets")
    public ResponseEntity<List<Secret>> getAllSecrets() {
        return ResponseEntity
                .ok()
                .body(secretService.getAllSecrets());
    }

}
