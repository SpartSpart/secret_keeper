package ru.spart.passwordkeeper.controller.model;

public class NewSecret {
    private String description;
    private String login;
    private String password;
    private int user_id;

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

    public int getUser_id() {return user_id;}

    public void setUser_id(int user_id) {this.user_id = user_id;}
}
