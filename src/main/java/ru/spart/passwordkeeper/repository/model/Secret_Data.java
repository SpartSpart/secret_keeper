package ru.spart.passwordkeeper.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "Secret_Data")

public class Secret_Data {

    @Id
    @GeneratedValue
    @Column (name = "ID")
    private long id;

    @Column (name = "S_DESCRIPTION")
    private String s_description;

    @Column (name = "S_LOGIN")
    private String s_login;

    @Column (name = "S_PASSWORD")
    private String s_password;

    @Column (name = "S_USER_ID")
    private long s_user_id;
}
