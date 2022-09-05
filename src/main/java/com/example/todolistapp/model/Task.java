package com.example.todolistapp.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Status status;
    @ManyToOne
    private Priority priority;
    private LocalDateTime finishDate;
    @ManyToOne
    private TasksList tasksList;

    public Task() {
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

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public TasksList getTasksList() {
        return tasksList;
    }

    public void setTasksList(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", status=" + status
                + ", finishDate=" + finishDate
                + ", tasksList=" + tasksList
                + ", priority=" + priority
                + '}';
    }
}
