package com.example.amagazishi.service.impl;

import com.example.amagazishi.exception.MassageSendException;
import com.example.amagazishi.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String mailName;

    private final JavaMailSender mailSender;

    @Override
    public void sendMessageTo(String email,String title, String content){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(mailName);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content);
            mailSender.send(mimeMessage);
        }catch (MessagingException e) {
            log.error(e.getMessage());
            throw new MassageSendException("error.messageSend");
        }
    }

}
