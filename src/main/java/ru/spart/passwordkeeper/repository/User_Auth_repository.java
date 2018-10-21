package ru.spart.passwordkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spart.passwordkeeper.controller.model.NewSecret;
import ru.spart.passwordkeeper.repository.model.User_Auth;

import java.util.List;

@Repository
public interface User_Auth_repository extends JpaRepository <User_Auth,Long> {

//    List<User> findAllUsers();
//
//    User findSecretById(int id);
}
