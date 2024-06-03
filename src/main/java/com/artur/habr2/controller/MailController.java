package com.artur.habr2.controller;

import com.artur.habr2.dto.request.SendMailRequest;
import com.artur.habr2.dto.response.MailResponse;
import com.artur.habr2.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService service;

    @GetMapping("/inbox")
    public List<MailResponse> inbox(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                    @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return service.inbox(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/outbox")
    public List<MailResponse> outbox(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                     @RequestParam(required = false,defaultValue = "10") int pageSize) {
        return service.outbox(PageRequest.of(pageNumber, pageSize));
    }

    @PostMapping("/send")
    public MailResponse send(@RequestBody SendMailRequest request) {
        return service.send(request);
    }
}