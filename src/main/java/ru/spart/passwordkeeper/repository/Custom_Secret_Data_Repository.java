package ru.spart.passwordkeeper.repository;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.controller.model.NewSecret;
import ru.spart.passwordkeeper.repository.model.Secret_Data;

@Repository
public class Custom_Secret_Data_Repository {


    public void add() {
        NewSecret secret = new NewSecret();
        secret.setDescription("1");
        secret.setLogin("1");
        secret.setPassword("1");
        secret.setUser_id(1);

    }
}
