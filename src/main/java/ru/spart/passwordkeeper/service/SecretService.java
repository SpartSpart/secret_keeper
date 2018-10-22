package ru.spart.passwordkeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spart.passwordkeeper.controller.model.Secret;
import ru.spart.passwordkeeper.repository.SecretDataRepository;
import ru.spart.passwordkeeper.repository.model.SecretData;

import java.util.List;
import java.util.Optional;

@Service
public class SecretService {

    @Autowired
    private SecretDataRepository secretRepository;

    public void add(Secret secret) {
        SecretData secret_data = new SecretData();

        secret_data.setDescription(secret.getDescription());
        secret_data.setLogin(secret.getLogin());
        secret_data.setPassword(secret.getPassword());
        secret_data.setUser_id(secret.getUser_id());

        secretRepository.saveAndFlush(secret_data);
    }

    public void update(long id, Secret secret) {
        Optional<SecretData> secret_data = secretRepository.findById(id); //need to learn Optional

        secret_data.get().setDescription(secret.getDescription());
        secret_data.get().setLogin(secret.getLogin());
        secret_data.get().setPassword(secret.getPassword());

        secretRepository.saveAndFlush(secret_data.get());

    }


    public Secret getSecret(long id) {
        Optional<SecretData> secret_data = secretRepository.findById(id);
        Secret secret = new Secret();

        secret.setId(secret_data.get().getId());
        secret.setDescription(secret_data.get().getDescription());
        secret.setLogin(secret_data.get().getLogin());
        secret.setPassword(secret_data.get().getPassword());
        secret.setUser_id(secret_data.get().getUser_id());

        return secret;
   }


    public void deleteSecret(long id) {
        secretRepository.deleteById(id);
    }


    public List<Secret> getAllSecrets() {
        List<Secret> allSecrets = null;
        List<SecretData> allSecretsData = secretRepository.findAll();

        for (SecretData secret : allSecretsData) {
            Secret newSecret = new Secret();
            newSecret.setId(secret.getId());
            newSecret.setDescription(secret.getDescription());
            newSecret.setLogin(secret.getLogin());
            newSecret.setPassword(secret.getPassword());
            newSecret.setUser_id(secret.getUser_id());
            allSecrets.add(newSecret);
        }

        return allSecrets;
    }
}
