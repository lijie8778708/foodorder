package com.jie.repository;

import com.jie.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
