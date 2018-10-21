package ru.spart.passwordkeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spart.passwordkeeper.controller.model.NewSecret;
import ru.spart.passwordkeeper.repository.Custom_Secret_Data_Repository;

@Service
public class SecretService {

    private Custom_Secret_Data_Repository secretRepository;

    public void add(NewSecret newSecret) {
        secretRepository.add();
    }
}
