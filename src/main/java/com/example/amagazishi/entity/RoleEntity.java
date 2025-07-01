package com.example.amagazishi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
public class RoleEntity extends BaseEntity implements GrantedAuthority {
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "m2m_users_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<UserEntity> userEntityList;

    @Override
    public String getAuthority() {
        return roleName;
    }

    public RoleEntity setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public RoleEntity setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
        return this;
    }
}
