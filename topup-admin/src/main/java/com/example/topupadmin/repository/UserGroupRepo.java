package com.example.topupadmin.repository;

import com.example.topupadmin.entity.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGroupRepo  extends JpaRepository<UserGroupEntity,String> {
    Optional<UserGroupEntity> findBySessionId(String id);
}
