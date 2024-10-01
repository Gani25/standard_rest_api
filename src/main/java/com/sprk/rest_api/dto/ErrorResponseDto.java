package com.sprk.rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;

    private int errorCode;

    private String errorMessage;

    private LocalDateTime errorTime;
}
