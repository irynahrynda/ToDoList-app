package com.example.todolistapp.config;

import com.example.todolistapp.model.Status;
import com.example.todolistapp.service.StatusService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final StatusService statusService;


    public DataInitializer(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostConstruct
    public void inject() {
        Status toDoStatus = new Status(Status.StatusName.TO_DO);
        statusService.createStatus(toDoStatus);

        Status inProgressStatus = new Status(Status.StatusName.IN_PROGRESS);
        statusService.createStatus(inProgressStatus);
        Status doneStatus = new Status(Status.StatusName.DONE);
        statusService.createStatus(doneStatus);
        Status terminatedStatus = new Status(Status.StatusName.TERMINATED);
        statusService.createStatus(terminatedStatus);
    }
}
