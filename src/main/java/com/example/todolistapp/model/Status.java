package com.example.todolistapp.model;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private StatusName statusName;

    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusName getStatusName() {
        return statusName;
    }

    public void setStatusName(StatusName statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", statusName=" + statusName +
                '}';
    }

    public enum StatusName {
        DO,
        IN_PROGRESS,
        DONE,
        TERMINATED
    }
}
