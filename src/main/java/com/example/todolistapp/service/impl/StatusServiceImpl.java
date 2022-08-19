package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Status;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.repository.StatusRepository;
import com.example.todolistapp.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public Status getStatusById(Long statusId) {
        return statusRepository.findById(statusId).orElseThrow(() ->
                new RuntimeException("Can't find status by id " + statusId));
    }

    @Override
    public List<Status> allStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status updateStatusById(Long statusId, Status status) {
        Status statusToUpdate = getStatusById(statusId);
        if (status.getStatusName() != null) {
            statusToUpdate.setStatusName(status.getStatusName());
        }

        return createStatus(statusToUpdate);
    }

    @Override
    public void deleteStatusById(Long statusId) {
        statusRepository.delete(getStatusById(statusId));
    }
}
