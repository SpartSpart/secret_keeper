package ru.spart.passwordkeeper.repository.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Secret")

public class SecretData {

    @Id
    @GeneratedValue (generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    @Column (name = "ID",nullable = false)
    private long id;

    @Column (name = "DESCRIPTION")
    private String description;

    @Column (name = "LOGIN")
    private String login;

    @Column (name = "PASSWORD")
    private String password;

    @Column (name = "USER_ID")
    private long user_id;

    public SecretData() {
    }

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
