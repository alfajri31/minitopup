package com.example.topupadmin.repository;

import com.example.topupadmin.model.User;

public interface UserGroupRedisRepo {
    void saveUser(User user);
}
