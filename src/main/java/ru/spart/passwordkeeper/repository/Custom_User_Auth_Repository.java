package ru.spart.passwordkeeper.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Custom_User_Auth_Repository {

    @Autowired
    private User_Auth_repository user_auth_repository;

}
