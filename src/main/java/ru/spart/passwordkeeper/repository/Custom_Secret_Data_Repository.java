package ru.spart.passwordkeeper.repository;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.controller.model.NewSecret;
import ru.spart.passwordkeeper.repository.model.Secret_Data;

import java.util.List;

@Repository
public class Custom_Secret_Data_Repository {

@Autowired
private Secret_Data_Repository secret_data_repository;

    private List<NewSecret> allSecrets;

    public void add(NewSecret secret) {
        secret.getDescription();
        secret.getLogin();
        secret.getPassword();
        secret.getUser_id();

    }

    public void update(long id,NewSecret secret) {
        NewSecret current_secret = secret_data_repository.findSecretById(id);
        current_secret = secret;

    }

    public NewSecret getSecret(long id) {
        return secret_data_repository.findSecretById(id);

    }

    public List<NewSecret> getAllSecrets() {
        return secret_data_repository.findAllSecrets();
    }
}
