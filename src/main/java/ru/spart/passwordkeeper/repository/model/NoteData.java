package ru.spart.passwordkeeper.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "Notes")
public class NoteData {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", nullable = false)
        private long id;

       @Column(name = "NOTE")
        private String note;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserData userData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
