package com.example.amagazishi.controller;


import com.example.amagazishi.dto.UserDtoResponse;
import com.example.amagazishi.exception.BaseException;
import com.example.amagazishi.mapper.UserMapper;
import com.example.amagazishi.service.AuthService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Slf4j
@Tag(name = "Аутентификация")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@Parameter(description = "Имя пользователя")
                        @RequestParam String username,
                        @Parameter(description = "Пароль пользователя")
                        @RequestParam String password
    ) throws BaseException {
        System.out.println("userName: "  + username + " Password: " + password);
        log.info("----->>>>>  получили запрос в систему ");
        return authService.login(username, password);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/current")
    public UserDtoResponse getCurrentAuthUser(){
        return UserMapper.toUserDtoResponse(authService.getCurrentUser());
    }

    @PostMapping("/password-restoration")
    public String passwordRestoration(@RequestParam String emailOrLogin){
        authService.passwordRestoration(emailOrLogin);
        Locale local = LocaleContextHolder.getLocale();
        return local.getLanguage().equals("ru")? "Вам на почту отправлен секретный код для восстановления пароля":
                local.getLanguage().equals("kg")? "Сырсөздү калыбына келтирүү үчүн жашыруун код сизге почта аркылуу жөнөтүлдү":
                        "A secret code to restore password has been sent to you by mail";
    }

    @PostMapping("/update-password/{active_code}")
    public String updatePassword(
            @PathVariable("active_code") String activeCode,
            @RequestParam String newPassword
    ){
        return authService.updatePassword(activeCode, newPassword);
    }
}