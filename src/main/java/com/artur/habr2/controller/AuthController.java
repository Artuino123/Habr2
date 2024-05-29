package com.artur.habr2.controller;

import com.artur.habr2.dto.request.SignInRequest;
import com.artur.habr2.dto.request.SignUpRequest;
import com.artur.habr2.dto.response.BooleanOperationResponse;
import com.artur.habr2.dto.response.TokenResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/sign-up")
    public BooleanOperationResponse singUp(@RequestBody SignUpRequest request) {
        return null;
    }

    @PutMapping("/sign-up/{code}")
    public BooleanOperationResponse singUpConfirm(@PathVariable String code) {
        return null;
    }

    @PostMapping("/sign-in")
    public TokenResponse signIn(@RequestBody SignInRequest request) {
        return null;
    }
    @PostMapping("sign-out")
    public BooleanOperationResponse exit() {
        return null;
    }
}
