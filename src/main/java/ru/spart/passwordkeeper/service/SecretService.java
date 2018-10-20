package ru.spart.passwordkeeper.service;

import org.springframework.stereotype.Service;
import ru.spart.passwordkeeper.controller.model.NewSecret;

@Service
public class SecretService {

    private final SecretRepository secretRepository;

    public void add(NewSecret newSecret) {
        secretRepository.add();
    }
}
