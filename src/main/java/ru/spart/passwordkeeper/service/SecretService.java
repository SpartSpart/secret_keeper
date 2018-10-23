package ru.spart.passwordkeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spart.passwordkeeper.controller.model.Secret;
import ru.spart.passwordkeeper.repository.SecretDataRepository;
import ru.spart.passwordkeeper.repository.model.SecretData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SecretService {

    @Autowired
    private SecretDataRepository secretRepository;

    public void add(Secret secret) {
        SecretData secretData = new SecretData();

        secretData.setDescription(secret.getDescription());
        secretData.setLogin(secret.getLogin());
        secretData.setPassword(secret.getPassword());
        secretData.setUserId(secret.getUserId());

        secretRepository.saveAndFlush(secretData);
    }

    public void update(long id, Secret secret) {
        Optional<SecretData> secretData = secretRepository.findById(id); //need to learn Optional//orElseThrow(secretnotfound::new)

        secretData.get().setDescription(secret.getDescription());
        secretData.get().setLogin(secret.getLogin());
        secretData.get().setPassword(secret.getPassword());

        secretRepository.saveAndFlush(secretData.get());
    }


    public Secret getSecret(long id) {
        Optional<SecretData> secretData = secretRepository.findById(id);
        Secret secret = new Secret();

        secret.setId(secretData.get().getId());
        secret.setDescription(secretData.get().getDescription());
        secret.setLogin(secretData.get().getLogin());
        secret.setPassword(secretData.get().getPassword());
        secret.setUserId(secretData.get().getUserId());

        return secret;
   }


    public void deleteSecret(long id) {
        secretRepository.deleteById(id);
    }


    public List<Secret> getAllSecrets() {
        List<Secret> allSecrets = new ArrayList<>();
        List<SecretData> allSecretsData = secretRepository.findAll();

        for (SecretData secret : allSecretsData) {
            Secret newSecret = new Secret();
            newSecret.setId(secret.getId());
            newSecret.setDescription(secret.getDescription());
            newSecret.setLogin(secret.getLogin());
            newSecret.setPassword(secret.getPassword());
            newSecret.setUserId(secret.getUserId());
            allSecrets.add(newSecret);
        }

        return allSecrets;
    }
}
