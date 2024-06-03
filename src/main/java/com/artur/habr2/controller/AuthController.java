package com.artur.habr2.controller;

import com.artur.habr2.dto.request.SignInRequest;
import com.artur.habr2.dto.request.SignUpRequest;
import com.artur.habr2.dto.response.BooleanOperationResponse;
import com.artur.habr2.dto.response.TokenResponse;
import com.artur.habr2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/sign-up")
    public BooleanOperationResponse singUp(@RequestBody SignUpRequest request) {
        return service.singUp(request);
    }

    @PostMapping("/sign-in")
    public TokenResponse signIn(@RequestBody SignInRequest request) {
        return service.signIn(request);
    }

    @PostMapping("/sign-out")
    public BooleanOperationResponse exit() {
        return service.signOut();
    }
}