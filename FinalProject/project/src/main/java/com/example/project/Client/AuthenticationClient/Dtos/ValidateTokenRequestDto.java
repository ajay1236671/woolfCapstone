package com.example.project.Client.AuthenticationClient.Dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private Long userId;
    private String token;
}
