package ru.spart.passwordkeeper.repository;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.controller.model.NewSecret;
import ru.spart.passwordkeeper.repository.model.Secret_Data;

import java.util.List;

@Repository
public interface Secret_Data_Repository extends JpaRepository<Secret_Data,Long> {

    List<NewSecret> findAllSecrets();

    NewSecret findSecretById(long id);

}
