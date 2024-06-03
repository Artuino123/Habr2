package com.artur.habr2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {
    private String authorEmail;
    private String targetEmail;
    private String title;
    private String text;
    private Instant date;
}
