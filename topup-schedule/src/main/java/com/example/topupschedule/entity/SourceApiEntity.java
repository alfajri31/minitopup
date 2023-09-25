package com.example.topupschedule.entity;


import com.example.topupschedule.entity.base.BaseEntityAI;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "source_api")
@Data
public class SourceApiEntity {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "sourceApiEntity",fetch = FetchType.LAZY)
    private Set<OperatorEntity> operatorEntities;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
}
