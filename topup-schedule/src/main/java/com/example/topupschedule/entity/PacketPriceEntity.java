package com.example.topupschedule.entity;


import com.example.topupschedule.entity.base.BaseEntityUUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "packet_price")
@Data
@EqualsAndHashCode(callSuper = true)
public class PacketPriceEntity extends BaseEntityUUID {
    private Double nominal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="packet_entity_id")
    private PacketEntity packetEntity;
}
