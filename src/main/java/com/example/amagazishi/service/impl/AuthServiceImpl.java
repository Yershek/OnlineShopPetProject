package com.example.amagazishi.service.impl;


import com.example.amagazishi.entity.UserEntity;
import com.example.amagazishi.exception.AuthorizeException;
import com.example.amagazishi.exception.InvalidPasswordRestore;
import com.example.amagazishi.repository.UserRepository;
import com.example.amagazishi.security.JwtHandler;
import com.example.amagazishi.service.AuthService;
import com.example.amagazishi.service.MailService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final JwtHandler jwtHandler;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new AuthorizeException("error.authorization"));
    }

    @Override
    public UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String login(String username, String password) {
        log.info("------>>>>> Пришел логин {}", username);
        log.info("------>>>>> Пришел пароль {}", password);
        UserEntity authUser = userRepository.findByUsername(username).orElseThrow(() -> new AuthorizeException("error.authorization"));
        log.info("------>>>>> Пришел пароль пользователя  {}", authUser.getPassword());
        if (!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new AuthorizeException("error.authorization");
        }
        return jwtHandler.jwtGenerator(authUser);
    }

    @Override
    public String logout() {
        throw new NotImplementedException();
    }

    @Override
    public void passwordRestoration(String emailOrLogin) {
        log.info("----.>>>>>> {} ", emailOrLogin);
        UserEntity user = userRepository.findByUsername(emailOrLogin).orElse(null);
        if (Objects.isNull(user)) {
            user = userRepository.findByEmail(emailOrLogin).orElseThrow(() -> new InvalidPasswordRestore("Неверные данные логина или почты"));
        }
        user.setActiveCode(UUID.randomUUID().toString());
        String titleMessage = LocaleContextHolder.getLocale().getLanguage().equals("ru") ? "Восстановление пароля для сайта pl99.kg" :
                LocaleContextHolder.getLocale().getLanguage().equals("kg") ? "PL99.kg веб-сайты үчүн сырсөздү калыбына келтирүү" :
                        "Password recovery for PL99.KG website";

        String message = LocaleContextHolder.getLocale().getLanguage().equals("ru") ? "Для восстановления пароля пройдите по данной ссылке http://195.38.165.33:8080/api/auth/update-password/" + user.getActiveCode() :
                LocaleContextHolder.getLocale().getLanguage().equals("kg") ? "Сырсөздү калыбына келтирүү үчүн, ушул шилтемеге өтүңүз http://195.38.165.33:8080/api/auth/update-password/" + user.getActiveCode() :
                        "To restore the password, go to this link http://195.38.165.33:8080/api/auth/update-password/" + user.getActiveCode();
        userRepository.save(user);
        mailService.sendMessageTo(user.getEmail(), titleMessage, message);
    }

    @Override
    public String updatePassword(String activeCode, String newPassword) {
        UserEntity user = userRepository.findByActiveCode(activeCode).orElseThrow(() -> new InvalidPasswordRestore("error.invalidPasswordRestore"));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setActiveCode(null);
        return "Пароль успешно изменен у пользователя под логином " + userRepository.save(user).getUsername();
    }
}