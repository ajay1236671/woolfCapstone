package com.example.AuthSecurityService.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {

    private Long userId;
    private String token;
}
