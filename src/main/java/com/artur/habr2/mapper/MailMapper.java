package com.artur.habr2.mapper;

import com.artur.habr2.dto.response.MailResponse;
import com.artur.habr2.model.MailEntity;
import org.springframework.stereotype.Component;

@Component
public class MailMapper {
    public MailResponse toDto(MailEntity mail) {
        return MailResponse.builder()
                .authorEmail(mail.getAuthor().getEmail())
                .targetEmail(mail.getTarget().getEmail())
                .title(mail.getTitle())
                .text(mail.getText())
                .date(mail.getDateTime())
                .build();
    }
}
