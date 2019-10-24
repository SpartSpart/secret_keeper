package ru.spart.appteka.controller.model;

import java.util.Date;

public class Drugs {
    private long id;
    private String name;
    private long type_id;
    private String count;
    private long appointment_id;
    private Date date;
    private long user_id;

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public long getType_id() {return type_id;}

    public void setType_id(long type_id) {this.type_id = type_id;}

    public String getCount() {return count;}

    public void setCount(String count) {this.count = count;}

    public long getAppointment_id() {return appointment_id;}

    public void setAppointment_id(long appointment_id) {this.appointment_id = appointment_id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public long getUser_id (long user_id) {return user_id;}

    public void setUser_id(long user_id) {this.user_id = user_id;}

}