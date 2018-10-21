package ru.spart.passwordkeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spart.passwordkeeper.controller.model.NewSecret;
import ru.spart.passwordkeeper.repository.Custom_Secret_Data_Repository;
import ru.spart.passwordkeeper.repository.Secret_Data_Repository;

import java.util.List;

@Service
public class SecretService {

    private Custom_Secret_Data_Repository secretRepository;

    public void add(NewSecret newSecret) {
        secretRepository.add(newSecret);
    }

    public void update(long id, NewSecret secret) {
        secretRepository.update(id,secret);
    }

    public NewSecret getSecret(long id) {
        return secretRepository.getSecret(id);

    }

    public List<NewSecret> getAllSecrets() {
        return secretRepository.getAllSecrets();
    }
}
