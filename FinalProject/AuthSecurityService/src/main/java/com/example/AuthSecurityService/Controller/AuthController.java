package com.example.AuthSecurityService.Controller;

import com.example.AuthSecurityService.Dto.UserDto;
import com.example.AuthSecurityService.Dto.ValidateTokenRequestDto;
import com.example.AuthSecurityService.Dto.ValidateTokenResponseDto;
import com.example.AuthSecurityService.Model.SessionStatus;
import com.example.AuthSecurityService.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login() {
        return null;
    }


    @PostMapping("/validate")
    public ResponseEntity<ValidateTokenResponseDto> validate(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        Optional<UserDto> userDto = authService.validate(validateTokenRequestDto.getToken(), validateTokenRequestDto.getUserId());

        if (userDto.isEmpty()) {
            ValidateTokenResponseDto validateTokenResponseDto = new ValidateTokenResponseDto();
            validateTokenResponseDto.setSessionStatus(SessionStatus.INVALID);
            return new ResponseEntity<>(validateTokenResponseDto, HttpStatus.OK);
        }

        ValidateTokenResponseDto validateTokenResponseDto = new ValidateTokenResponseDto();
        validateTokenResponseDto.setSessionStatus(SessionStatus.ACTIVE);
        validateTokenResponseDto.setUserDto(userDto.get());

        return new ResponseEntity<>(validateTokenResponseDto, HttpStatus.OK);
    }


}
