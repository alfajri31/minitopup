package com.example.topupschedule.repository;

import com.example.topupschedule.entity.SourceApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceApiRepository extends JpaRepository<SourceApiEntity,Long> {
}
