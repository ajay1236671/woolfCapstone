package com.example.AuthSecurityService.Service;

import com.example.AuthSecurityService.Dto.UserDto;
import com.example.AuthSecurityService.Exceptions.UserAlreadyExistsException;
import com.example.AuthSecurityService.Exceptions.UserDoesNotExistsException;
import com.example.AuthSecurityService.Model.Session;
import com.example.AuthSecurityService.Model.SessionStatus;
import com.example.AuthSecurityService.Model.User;
import com.example.AuthSecurityService.Repository.SessionRepository;
import com.example.AuthSecurityService.Repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SessionRepository sessionRepository;


    public ResponseEntity<UserDto> login(String emial, String password) throws UserDoesNotExistsException {

        Optional<User> userOptional = userRepository.findByEmail(emial);

        if (userOptional.isEmpty()) {
            throw new UserDoesNotExistsException();
        }

        User user = userOptional.get();

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String token = RandomStringUtils.randomAscii(20);
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add("AUTH_TOKEN", token);

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        UserDto userDto = UserDto.from(user);
        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);
        return response;
    }

    public ResponseEntity<Void> logout(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_id(token, userId);

        if (sessionOptional.isEmpty()) {
            return null;
        }

        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.LOGGED_OUT);
        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }


    public UserDto signUp(String email, String password) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isEmpty()) {
            throw new UserAlreadyExistsException("User already exist");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }

    public SessionStatus validate(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_id(token, userId);
        if (sessionOptional.isEmpty()) {
            return SessionStatus.INVALID;
        }

        Session session = sessionOptional.get();
        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.EXPIRED;
        }

        return SessionStatus.ACTIVE;
    }
}
