package com.example.amagazishi.controller;

import com.example.amagazishi.service.MailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/send-message-to-mail")
    public ResponseEntity<String> sendMassage(
            @RequestParam String email,
            @RequestParam String title,
            @RequestParam String content
    ){
        mailService.sendMessageTo(email,title,content);
        return ResponseEntity.ok("ваще сообщение отправлено");
    }

}
