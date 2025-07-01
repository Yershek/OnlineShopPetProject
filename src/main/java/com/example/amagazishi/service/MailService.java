package com.example.amagazishi.service;

import com.example.amagazishi.entity.MailEntity;

import java.util.List;

public interface MailService {
    void sendMessageTo(String email,String title, String content);
    void sendReportMessage(String email,String title, String content);
    List<MailEntity> getAllMessages();
    MailEntity getMessageById(Long id);
    List<MailEntity> getMessagesByTitle(String title);
}
