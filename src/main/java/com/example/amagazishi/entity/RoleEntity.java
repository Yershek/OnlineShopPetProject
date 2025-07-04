package com.example.amagazishi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@With
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "roles")
public class RoleEntity extends BaseEntity implements GrantedAuthority {
    @Column(name = "role_name")
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

}
