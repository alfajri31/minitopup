package com.example.topupschedule.entity;


import com.example.topupschedule.entity.base.BaseEntityUUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "packet")
@Data
@EqualsAndHashCode(callSuper = true)
public class PacketEntity extends BaseEntityUUID {
    private String name;
    @Column(nullable = false,columnDefinition = "boolean default true")
    private Boolean status;
    @OneToMany(mappedBy = "packetEntity",fetch = FetchType.LAZY)
    private Set<PacketPriceEntity> packetPriceEntities;
    @Column(nullable = false,columnDefinition = "integer default 0")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_entity_id")
    private OperatorEntity operatorEntity;
}
