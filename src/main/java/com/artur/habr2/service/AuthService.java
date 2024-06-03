package com.artur.habr2.service;

import com.artur.habr2.dto.request.SignInRequest;
import com.artur.habr2.dto.request.SignUpRequest;
import com.artur.habr2.dto.response.BooleanOperationResponse;
import com.artur.habr2.dto.response.TokenResponse;
import com.artur.habr2.exception.HabrServiceException;
import com.artur.habr2.model.UserEntity;
import com.artur.habr2.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public BooleanOperationResponse singUp(SignUpRequest request) {
        if (request.getEmail().isBlank()) {
            throw new HabrServiceException("EMPTY_EMAIL","ВВЕДЕН ПУСТОЙ EMAIL", HttpStatus.BAD_REQUEST);
        }
        if (userEntityRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new HabrServiceException("TAKEN_EMAIL","ЭТОТ EMAIL ЗАНЯТ", HttpStatus.BAD_REQUEST);
        }
        if (request.getPassword().isBlank()) {
            throw new HabrServiceException("EMPTY_PASSWORD","ВВЕДЕН ПУСТОЙ ПАРОЛЬ",HttpStatus.BAD_REQUEST);
        }
        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userEntityRepository.save(user);
        return new BooleanOperationResponse(true);
    }

    public TokenResponse signIn(SignInRequest request) {
        Optional<UserEntity> optionalUser = userEntityRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            throw new HabrServiceException("WRONG_EMAIL","ВВЕДЕН НЕВЕРНЫЙ EMAIL", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = optionalUser.get();
        if (passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            String token = UUID.randomUUID().toString();
            userEntity.setToken("Bearer " + token);
            userEntityRepository.save(userEntity);
            return new TokenResponse(token);
        } else {
            throw new HabrServiceException("WRONG_PASSWORD","ВВЕДЕН НЕВЕРНЫЙ ПАРОЛЬ",HttpStatus.BAD_REQUEST);
        }
    }

    public BooleanOperationResponse signOut(){
        UserEntity user = getCurrentUser();
        user.setToken(null);
        userEntityRepository.save(user);
        return new BooleanOperationResponse(true);
    }

    public UserEntity getCurrentUser() {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof  UserDetails e) {
            return userEntityRepository.findByEmail(e.getUsername()).orElseThrow();
        }
        throw new HabrServiceException("NOT_LOGGED_IN", "Не вошел в аккаунт", HttpStatus.UNAUTHORIZED);
    }
}