package com.artur.habr2.service;

import com.artur.habr2.dto.request.SendMailRequest;
import com.artur.habr2.dto.response.MailResponse;
import com.artur.habr2.exception.HabrServiceException;
import com.artur.habr2.mapper.MailMapper;
import com.artur.habr2.model.MailEntity;
import com.artur.habr2.model.UserEntity;
import com.artur.habr2.repository.MailEntityRepository;
import com.artur.habr2.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailEntityRepository mailEntityRepository;
    private final AuthService authService;
    private final MailMapper mapper;
    private final UserEntityRepository userEntityRepository;

    public List<MailResponse> inbox(Pageable pageable) {
        UserEntity user = authService.getCurrentUser();
        return mailEntityRepository.findAllByTargetOrderByDateTimeDesc(user, pageable)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<MailResponse> outbox(Pageable pageable) {
        UserEntity user = authService.getCurrentUser();
        return mailEntityRepository.findAllByAuthorOrderByDateTimeDesc(user,pageable).stream()
                .map(mapper::toDto)
                .toList();
    }

    public MailResponse send(SendMailRequest request) {
        UserEntity author = authService.getCurrentUser();
        Optional<UserEntity> optionalUser = userEntityRepository.findByEmail(request.getTargetEmail());
        if (optionalUser.isEmpty()) {
            throw new HabrServiceException("WRONG_EMAIL", "Неправильно введен адрес получателя", HttpStatus.BAD_REQUEST);
        }
        UserEntity target = optionalUser.get();
        MailEntity mail = MailEntity.builder()
                .author(author)
                .target(target)
                .title(request.getTitle())
                .text(request.getText())
                .dateTime(Instant.now())
                .build();
        return mapper.toDto(mailEntityRepository.save(mail));
    }
}