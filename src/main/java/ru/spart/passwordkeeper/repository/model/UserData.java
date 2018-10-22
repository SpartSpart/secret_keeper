package ru.spart.passwordkeeper.repository.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

    @Entity
    @Table(name = "User")
    public class UserData {

        @GeneratedValue (generator = "increment")
        @GenericGenerator(name = "increment",strategy = "increment")
        @Column (name = "ID",nullable = false)
        private long id;

        @Column(name = "USER_LOGIN")
        private String user_login;

        @Column(name = "USER_PASSWORD")
        private String user_password;

        public UserData() {}

        public long getId() {return id;}

        public void setId(long id) {
            this.id = id;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getUser_password() {
            return user_password;
        }

        public void setUser_password(String user_password) {
            this.user_password = user_password;
        }
    }


