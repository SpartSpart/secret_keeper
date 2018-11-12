package ru.spart.passwordkeeper.repository.model;

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

        @Column(name = "USER_EMAIL")
        private String userEmail;

        public UserData() {}

        public long getId() {return id;}

        public String getUserLogin() {return userLogin;}

        public void setUserLogin(String userLogin) {
            this.userLogin = userLogin;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserEmail() {return userEmail;}

        public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    }


