package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Priority;
import com.example.todolistapp.repository.PriorityRepository;
import com.example.todolistapp.service.PriorityService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PriorityServiceImpl implements PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public Priority createPriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    @Override
    public Priority getPriorityById(Long priorityId) {
        return priorityRepository.findById(priorityId).orElseThrow(() ->
                new RuntimeException("Can't get priority by id " + priorityId));
    }

    @Override
    public List<Priority> allPriorities() {
        return priorityRepository.findAll();
    }

    @Override
    public Priority updatePriorityById(Long priorityId, Priority priority) {
        Priority priorityToUpdate = getPriorityById((priorityId));
        if (priority.getPriorityName() != null) {
            priorityToUpdate.setPriorityName(priority.getPriorityName());
        }
        return createPriority(priorityToUpdate);
    }

    @Override
    public void deletePriorityById(Long priorityId) {
        priorityRepository.delete(getPriorityById(priorityId));

    }

    @Override
    public Priority getPriorityByName(Priority.PriorityName priorityName) {
        return priorityRepository.findByPriorityName(priorityName).orElseThrow(() ->
                new RuntimeException("Can't get priority by priority name " + priorityName));
    }
}
