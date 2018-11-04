package ru.spart.passwordkeeper.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spart.passwordkeeper.controller.model.Secret;
import ru.spart.passwordkeeper.repository.SecretDataRepository;
import ru.spart.passwordkeeper.repository.UserDataRepository;
import ru.spart.passwordkeeper.repository.model.SecretData;
import ru.spart.passwordkeeper.repository.model.UserData;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecretService {

    private final SecretDataRepository secretRepository;
    private final UserDataRepository userDataRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecretService(SecretDataRepository secretRepository, UserDataRepository userDataRepository, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.secretRepository = secretRepository;
        this.userDataRepository = userDataRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Transactional
    public void add(Secret secret) {
        SecretData secretData = new SecretData();
        secretData.setDescription(secret.getDescription());
        secretData.setLogin(secret.getLogin());
        secretData.setPassword(secret.getPassword());
        secretData.setUserData(getUserData());

        secretRepository.saveAndFlush(secretData);
    }

    @Transactional
    public void update(long id, Secret secret) throws SecretNotFound {
        SecretData secretData = secretRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(SecretNotFound::new);

        secretData.setDescription(secret.getDescription());
        secretData.setLogin(secret.getLogin());
        secretData.setPassword(secret.getPassword());

        secretRepository.saveAndFlush(secretData);
    }

    @Transactional(readOnly = true)
    public Secret getSecret(long id) throws SecretNotFound {
        SecretData secretData = secretRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(SecretNotFound::new);
        Secret secret = new Secret();

        secret.setId(secretData.getId());
        secret.setDescription(secretData.getDescription());
        secret.setLogin(secretData.getLogin());
        secret.setPassword(secretData.getPassword());

        return secret;
    }

    @Transactional
    public void deleteSecret(long id) throws SecretNotFound {
        SecretData secretData = secretRepository.findByIdAndUserData(id, getUserData())
                .orElseThrow(SecretNotFound::new);
        secretRepository.deleteById(secretData.getId());
    }

    @Transactional
    public List<Secret> getAllSecrets() {
        List<Secret> allSecrets = new ArrayList<>();
        List<SecretData> allSecretsData = secretRepository.findAllByUserData(getUserData());

        for (SecretData secret : allSecretsData) {
            Secret newSecret = new Secret();
            newSecret.setId(secret.getId());
            newSecret.setDescription(secret.getDescription());
            newSecret.setLogin(secret.getLogin());
            newSecret.setPassword(secret.getPassword());
            allSecrets.add(newSecret);
        }

        return allSecrets;
    }

    private UserData getUserData() {
        UserDetails userDetails = userDetailsServiceImpl.getCurrent();
        return userDataRepository.findByUserLogin(userDetails.getUsername())
                .orElseThrow(RuntimeException::new);
    }
}
