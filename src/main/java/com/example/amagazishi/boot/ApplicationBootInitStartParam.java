package com.example.amagazishi.boot;

import com.example.amagazishi.entity.RoleEntity;
import com.example.amagazishi.entity.UserEntity;
import com.example.amagazishi.enums.Active;
import com.example.amagazishi.repository.RoleRepository;
import com.example.amagazishi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationBootInitStartParam implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationBootInitStartParam(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<RoleEntity> roleEntitySet = roleRepository.findAll();
        if(userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = UserEntity
                    .builder()
                    .password(passwordEncoder.encode("admin"))
                    .username("admin")
                    .roles(roleEntitySet)
                    .email("admin@admin.com")
                    .build();
            userRepository.save(admin);
        }
    }
}
