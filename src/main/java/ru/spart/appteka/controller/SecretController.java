package ru.spart.appteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.spart.appteka.controller.model.Secret;
import ru.spart.appteka.service.SecretNotFound;
import ru.spart.appteka.service.SecretService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SecretController {

    private final SecretService secretService;


    @Autowired
    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/secrets")
    public ResponseEntity<Long> addSecret(@RequestBody Secret secret) {
        return ResponseEntity
                .ok()
                .body(secretService.add(secret));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/secrets/{id}")
    public ResponseEntity<Void> updateSecret(@PathVariable("id") long id, @RequestBody Secret secret) {
        try {
            secretService.update(id, secret);
        } catch (SecretNotFound secretNotFound) {
            throw new ApiNotFound();
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/secrets")
    public ResponseEntity<Void> updateAllSecrets(@RequestBody List<Secret> secrets) throws SecretNotFound {
        secretService.updateAll(secrets);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/secrets/{id}")
    public ResponseEntity<Secret> getSecret(@PathVariable("id") long id) {
        try {
            return ResponseEntity
                    .ok()
                    .body(secretService.getSecret(id));
        } catch (SecretNotFound secretNotFound) {
            throw new ApiNotFound();
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/secrets/{id}")
    public ResponseEntity<Void> deleteSecret(@PathVariable("id") long id) {
        try {
            secretService.deleteSecret(id);
        } catch (SecretNotFound secretNotFound) {
            throw new ApiNotFound();
        }
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/secrets")
    public ResponseEntity<List<Secret>> getAllSecrets() {
        return ResponseEntity
                .ok()
                .body(secretService.getAllSecrets());
    }

}
