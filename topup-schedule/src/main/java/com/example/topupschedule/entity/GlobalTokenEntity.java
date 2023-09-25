package com.example.topupschedule.entity;

import com.example.topupschedule.entity.base.BaseEntityAI;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "global_token")
@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalTokenEntity extends BaseEntityAI {
    @Column(name = "access_token",columnDefinition = "TEXT")
    private String accessToken;
}
