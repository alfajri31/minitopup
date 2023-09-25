package com.example.topupschedule.entity;


import com.example.topupschedule.entity.base.BaseEntityUUID;
import com.example.topupschedule.entity.partial.PartialOperatorBulkUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "operator")
@Data
@EqualsAndHashCode(callSuper = true)
@SqlResultSetMapping(
        name="partialOperatorBulkUpdate", classes = {
            @ConstructorResult(targetClass = PartialOperatorBulkUpdate.class, columns = {
                    @ColumnResult(name="name",type = String.class),
                    @ColumnResult(name="source_api_entity_id",type = Long.class),
                    @ColumnResult(name="operator_source_id",type = String.class)
            })
        })
public class OperatorEntity extends BaseEntityUUID {
    private String name;
    @Column(name = "operator_source_id")
    private String operatorSourceId;
    @OneToMany(mappedBy = "operatorEntity",fetch = FetchType.LAZY)
    private Set<PacketEntity> packetEntities;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_api_entity_id")
    private SourceApiEntity sourceApiEntity;
}
