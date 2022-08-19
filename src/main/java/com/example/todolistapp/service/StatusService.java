package com.example.todolistapp.service;

import com.example.todolistapp.model.Status;
import java.util.List;

public interface StatusService {
    Status createStatus(Status status);

    Status getStatusById(Long statusId);

    List<Status> allStatuses();

    Status updateStatusById(Long statusId, Status status);

    void deleteStatusById(Long statusId);

    Status getStatusByName(Status.StatusName statusName);
}
