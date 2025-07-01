package com.example.amagazishi.repository;

import com.example.amagazishi.entity.RoleEntity;
import com.example.amagazishi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(String roleName);
    Optional<RoleEntity> findByUserEntityList(UserEntity user);
}
