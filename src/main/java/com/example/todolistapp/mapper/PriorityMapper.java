package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.request.PriorityRequestDto;
import com.example.todolistapp.dto.response.PriorityResponseDto;
import com.example.todolistapp.model.Priority;
import com.example.todolistapp.service.PriorityService;
import org.springframework.stereotype.Component;

@Component
public class PriorityMapper {
    private final PriorityService priorityService;

    public PriorityMapper(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    public PriorityResponseDto mapToDto(Priority priority) {
        PriorityResponseDto priorityResponseDto = new PriorityResponseDto();
        if (priority.getPriorityName() != null) {
            priorityResponseDto.setId(priority.getId());
        }

        priorityResponseDto.setPriorityName(priority.getPriorityName().name());
        return priorityResponseDto;
    }

    public Priority mapToModel(PriorityRequestDto priorityRequestDto) {
        Priority priority = new Priority();
        priority.setPriorityName(priorityService.getPriorityByName(
                priority.getPriorityName()).getPriorityName());
        return priority;
    }
}
