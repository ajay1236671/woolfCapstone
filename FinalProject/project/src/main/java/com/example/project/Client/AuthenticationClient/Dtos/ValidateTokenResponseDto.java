package com.example.project.Client.AuthenticationClient.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
