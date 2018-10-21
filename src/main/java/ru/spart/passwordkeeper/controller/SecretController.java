package ru.spart.passwordkeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spart.passwordkeeper.controller.model.NewSecret;
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

    @PostMapping(value = "/secret")
    public ResponseEntity<Void> addSecret(@RequestBody NewSecret newSecret) {
        secretService.add(newSecret);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/secret/{id}")
    public ResponseEntity<Void> updateSecret(@PathVariable("id") long id, @RequestBody NewSecret newSecret) {
        secretService.update(id, newSecret);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/secret/{id}")
    public ResponseEntity<NewSecret> getSecret(@PathVariable("id") long id) {
        secretService.getSecret(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/secret/{id}")
    public ResponseEntity<Void> deleteSecret(@PathVariable("id") long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/secret/getAll")
    public ResponseEntity<List<NewSecret>> getAllSecrets() {
        secretService.getAllSecrets();
        return ResponseEntity.ok().build();
    }

}
