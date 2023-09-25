package com.example.topupschedule.repository;

import com.example.topupschedule.entity.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository  extends JpaRepository<UserGroupEntity,String> {
}
