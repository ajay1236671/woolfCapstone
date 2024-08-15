package com.example.AuthSecurityService.Controller;

import com.example.AuthSecurityService.Dto.*;
import com.example.AuthSecurityService.Exceptions.UserAlreadyExistsException;
import com.example.AuthSecurityService.Exceptions.UserDoesNotExistsException;
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
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto requestDto) throws UserDoesNotExistsException {
        return authService.login(requestDto.getEmail(), requestDto.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        return authService.logout(logoutRequestDto.getToken(), logoutRequestDto.getUserId());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {
        UserDto userDto = authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
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
