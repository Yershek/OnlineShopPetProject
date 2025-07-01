package com.example.amagazishi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class UserEntity extends BaseEntity implements UserDetails {
    private String username;
    private String activeCode;
    private String email;
    private String password;
    @ManyToMany(mappedBy = "userEntityList",fetch = FetchType.EAGER)
    private List<RoleEntity> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserEntity setActiveCode(String activeCode) {
        this.activeCode = activeCode;
        return this;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
}
