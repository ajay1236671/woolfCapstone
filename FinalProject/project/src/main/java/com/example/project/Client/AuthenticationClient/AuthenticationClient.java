package com.example.project.Client.AuthenticationClient;

import com.example.project.Client.AuthenticationClient.Dtos.ValidateTokenRequestDto;
import com.example.project.Client.AuthenticationClient.Dtos.ValidateTokenResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationClient {
    RestTemplateBuilder templateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.templateBuilder = restTemplateBuilder;
    }

    public ValidateTokenResponseDto validate(String token, Long userId) {
        RestTemplate restTemplate = templateBuilder.build();
        ValidateTokenRequestDto validateTokenRequestDto = new ValidateTokenRequestDto();
        validateTokenRequestDto.setUserId(userId);
        validateTokenRequestDto.setToken(token);

        ResponseEntity<ValidateTokenResponseDto> response = restTemplate.postForEntity
                ("http://localhost:8080/auth/validate",
                        validateTokenRequestDto,
                        ValidateTokenResponseDto.class);

        return response.getBody();
    }

}
