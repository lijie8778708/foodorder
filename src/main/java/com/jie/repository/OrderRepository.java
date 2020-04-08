package com.jie.repository;

import com.jie.entity.Order;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderRepository {

    public void save(Order order);
    public List<Order> findAllByUid(long uid, int index, int limit);
    public int countByUid(long uid);
    public List<Order> findAllByState(int state, int index, int limit);
    public int countByState(int state);
    public void updateState(long id, long aid, int state);

    @DeleteMapping("/deleteByMid/{mid}")
    public void deleteByMid(@PathVariable long mid);

    @DeleteMapping("/deleteByUid/{uid}")
    public void deleteByUid(@PathVariable long uid);
}
