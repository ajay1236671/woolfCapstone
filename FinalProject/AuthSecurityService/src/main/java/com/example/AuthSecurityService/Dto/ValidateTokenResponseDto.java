package com.example.AuthSecurityService.Dto;

import com.example.AuthSecurityService.Model.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
