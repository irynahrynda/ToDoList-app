package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.response.StatusResponseDto;
import com.example.todolistapp.model.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public StatusResponseDto mapToDto(Status status) {
        StatusResponseDto statusResponseDto = new StatusResponseDto();
        if (status.getStatusName() != null) {
            statusResponseDto.setId(status.getId());
        }
        statusResponseDto.setStatusName(status.getStatusName().name());
        return statusResponseDto;
    }
}
