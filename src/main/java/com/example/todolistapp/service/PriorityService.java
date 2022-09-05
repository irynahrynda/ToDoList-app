package com.example.todolistapp.service;

import com.example.todolistapp.model.Priority;
import java.util.List;

public interface PriorityService {
    Priority createPriority(Priority priority);

    Priority getPriorityById(Long priorityId);

    List<Priority> allPriorities();

    Priority updatePriorityById(Long priorityId, Priority priority);

    void deletePriorityById(Long priorityId);

    Priority getPriorityByName(Priority.PriorityName priorityName);
}
