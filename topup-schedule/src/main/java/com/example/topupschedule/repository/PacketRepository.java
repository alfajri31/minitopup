package com.example.topupschedule.repository;

import com.example.topupschedule.entity.PacketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacketRepository extends JpaRepository<PacketEntity,String> {
    PacketEntity findByName(String name);
}
