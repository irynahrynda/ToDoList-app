package com.example.todolistapp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "taskslists")
public class TasksList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Status status;
    private LocalDateTime deadline;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "tasksList")
    private List<Task> tasks;
    @ManyToOne
    private User user;

    private long counter;

    public TasksList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "TasksList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", deadline=" + deadline +
                ", tasks=" + tasks +
                ", user=" + user +
                ", counter=" + counter +
                '}';
    }
}
