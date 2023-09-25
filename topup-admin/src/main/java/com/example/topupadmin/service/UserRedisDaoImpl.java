package com.example.topupadmin.service;

import com.example.topupadmin.entity.UserGroupEntity;
import com.example.topupadmin.model.User;
import com.example.topupadmin.repository.UserGroupRedisRepo;
import com.example.topupadmin.repository.UserGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserRedisDaoImpl implements UserGroupRedisRepo {


    private final String hashReference= "UserGroupEntity";

    @Autowired
    private UserGroupRepo userGroupRepo;

    @Resource(name="redisTemplate")          // 'redisTemplate' is defined as a Bean in AppConfig.java
    private HashOperations<String, String, UserGroupEntity> hashOperations;

    public void saveUser(User user) {
        UserGroupEntity userGroupEntity = new UserGroupEntity();
        userGroupEntity.setSessionId(user.getUserId());
        userGroupEntity.setCreatedAt(new Date());
        hashOperations.putIfAbsent(hashReference, userGroupEntity.getSessionId(), userGroupEntity);
    }

    @Cacheable("user")
    public List<UserGroupEntity> findAll() {
        doLongRunningTask();
        return userGroupRepo.findAll();
    }

    private void doLongRunningTask() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
