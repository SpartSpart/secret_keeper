package ru.spart.passwordkeeper.repository.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

    @Entity
    @Table(name = "Users")
    public class UserData {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "ID",nullable = false)
        private long id;

        @Column(name = "USER_LOGIN")
        private String userLogin;

        @Column(name = "USER_PASSWORD")
        private String userPassword;

        public UserData() {}

        public long getId() {return id;}

        public void setId(long id) {
            this.id = id;
        }

        public String getUserLogin() {
            return userLogin;
        }

        public void setUserLogin(String userLogin) {
            this.userLogin = userLogin;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }
    }


