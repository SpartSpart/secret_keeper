package ru.spart.appteka.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "Type")
public class TypeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID",nullable = false)
    private long id;

    @Column(name = "TYPE")
    private String type;

    public TypeData() {}

    public long getId() {return id;}

    public String getType() {return type;}

    public void setType(String type) {
        this.type = type;
    }


}

