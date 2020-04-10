package com.jie.repository;

import com.jie.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserRepository {

    public List<User> findUser(int page, int limit);
    public int count();
    public void save(@RequestBody User user);
    public void deleteById(long id);
    public User login(String username, String password);
}
