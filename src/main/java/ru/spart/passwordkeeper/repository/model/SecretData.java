package ru.spart.passwordkeeper.repository.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Secrets")

public class SecretData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID",nullable = false)
    private long id;

    @Column (name = "DESCRIPTION")
    private String description;

    @Column (name = "LOGIN")
    private String login;

    @Column (name = "PASSWORD")
    private String password;

    @Column (name = "USER_ID")
    private long userId;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
