package com.artur.habr2.security;

import com.artur.habr2.controller.HabrExceptionHandler;
import com.artur.habr2.exception.HabrServiceException;
import com.artur.habr2.model.UserEntity;
import com.artur.habr2.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserEntityRepository repository;

    //
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles("User")
                .build();
    }
}