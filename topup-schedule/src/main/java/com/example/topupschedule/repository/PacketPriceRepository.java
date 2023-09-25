package com.example.topupschedule.repository;

import com.example.topupschedule.entity.PacketPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacketPriceRepository extends JpaRepository<PacketPriceEntity,String> {
}
