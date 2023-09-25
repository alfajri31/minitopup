package com.example.topupschedule.repository;

import com.example.topupschedule.entity.UserEntity;
import com.example.topupschedule.entity.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
