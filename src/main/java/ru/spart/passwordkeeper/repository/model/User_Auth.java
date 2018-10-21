package ru.spart.passwordkeeper.repository.model;

import javax.persistence.*;

    @Entity
    @Table(name = "User_Auth")
    public class User_Auth {

        @Id
        @Column(name = "ID")
        private long id;

        @Column(name = "USER_LOGIN")
        private String user_login;

        @Column(name = "USER_PASSWORD")
        private String user_password;

    }


