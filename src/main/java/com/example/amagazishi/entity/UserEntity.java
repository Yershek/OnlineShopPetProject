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
    private String username;
    @Column(name = "active_code")
    private String activeCode;
    private String email;
    private String password;
    @ManyToMany(mappedBy = "userEntityList",fetch = FetchType.EAGER)
    private List<RoleEntity> roles;
    @OneToOne
    @JoinColumn(name = "basket_id", unique = true)
    private BasketEntity basket;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

}
