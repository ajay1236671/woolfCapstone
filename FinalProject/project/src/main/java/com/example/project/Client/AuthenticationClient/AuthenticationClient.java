package com.example.project.Client.AuthenticationClient;

import com.example.project.Client.AuthenticationClient.Dtos.ValidateTokenResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationClient {

    public ValidateTokenResponseDto validate(String token, Long userId) {
        return null;
    }

}
