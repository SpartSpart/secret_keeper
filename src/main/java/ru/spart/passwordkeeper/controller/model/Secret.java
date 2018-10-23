package ru.spart.passwordkeeper.controller.model;

public class Secret {

    private long id;
    private String description;
    private String login;
    private String password;
    private long userId;

    public String getDescription() {return description;}

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

    public long getUserId() {return userId;}

    public void setUserId(long userId) {this.userId = userId;}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}
}
