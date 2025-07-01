package com.example.amagazishi.service.impl;

import com.example.amagazishi.entity.MailEntity;
import com.example.amagazishi.exception.MassageSendException;
import com.example.amagazishi.exception.MessageIsNotFoundException;
import com.example.amagazishi.repository.MailRepository;
import com.example.amagazishi.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String mailName;

    private final JavaMailSender mailSender;
    private final MailRepository mailRepository;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender, MailRepository mailRepository) {
        this.mailSender = mailSender;
        this.mailRepository = mailRepository;
    }

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

    @Override
    public void sendReportMessage(String email, String title, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(mailName);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content);
            mailSender.send(mimeMessage);
            MailEntity mailEntity = new MailEntity();
            mailEntity.setTitle(title);
            mailEntity.setContent(content);
            mailEntity.setEmailTo(mailName);
            mailRepository.save(mailEntity);
        }catch (MessagingException e) {
            log.error(e.getMessage());
            throw new MassageSendException("error.messageSend");
        }
    }

    @Override
    public List<MailEntity> getAllMessages() {
        return mailRepository.findAll();
    }

    @Override
    public MailEntity getMessageById(Long id) {
        return mailRepository.findById(id).orElseThrow(() -> new MessageIsNotFoundException("error.messageIsNotFound"));
    }

    @Override
    public List<MailEntity> getMessagesByTitle(String title) {
        return mailRepository.findByTitle(title);
    }

}
