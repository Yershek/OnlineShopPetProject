package com.example.amagazishi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@With
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(name = "username")
    private String username;
    @Column(name = "active_code")
    private String activeCode;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @ManyToMany(mappedBy = "userEntityList",fetch = FetchType.EAGER)
    private List<RoleEntity> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

}
