package ru.spart.appteka.repository.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Drugs")

public class DrugsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "NAME")
    private String name;

    //@ManyToOne
    @Column(name = "TYPE_ID")
    private long type_id;

    @Column(name = "COUNT")
    private String count;

   // @ManyToOne
    @Column(name = "APPOINTMENT_ID")
    private long appointment_id;

    @Column(name = "DATE")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserData userData;

    public DrugsData() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getType_id() {
        return type_id;
    }

    public void setType_id(long type_id) {
        this.type_id = type_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public long getAppointent_id() {
        return appointment_id;
    }

    public void setAppointent_id(long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
