package com.example.topupadmin.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users_group")
@Component
@Data
public class UserGroupEntity implements Serializable {
    @Id
    @GeneratedValue( generator = "uuid" )
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name="email")
    public String email;
    @Column(name="username")
    public String username;
    @Column(name="password")
    public String password;
    @Column(name="role")
    public Integer role;
    @Column(name="cookie_id")
    private String cookieId;
    @Column(name="ip_address")
    private String ipAddress;
    @Column(name="session_id")
    private String sessionId;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
