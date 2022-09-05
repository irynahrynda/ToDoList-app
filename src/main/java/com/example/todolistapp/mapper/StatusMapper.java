package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.request.StatusRequestDto;
import com.example.todolistapp.dto.response.StatusResponseDto;
import com.example.todolistapp.model.Status;
import com.example.todolistapp.service.StatusService;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    private final StatusService statusService;

    public StatusMapper(StatusService statusService) {
        this.statusService = statusService;
    }

    public StatusResponseDto mapToDto(Status status) {
        StatusResponseDto statusResponseDto = new StatusResponseDto();
        if (status.getStatusName() != null) {
            statusResponseDto.setId(status.getId());
        }
        statusResponseDto.setStatusName(status.getStatusName().name());
        return statusResponseDto;
    }

    public Status mapToModel(StatusRequestDto statusRequestDto) {
        Status status = new Status();
        status.setStatusName(statusService.getStatusByName(status.getStatusName()).getStatusName());
        return status;
    }
}
