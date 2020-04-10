package com.jie.repository;

import com.jie.entity.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderRepository {

    @PostMapping("/order/save")
    public void save(@RequestBody Order order);
    public List<Order> findAllByUid(long uid, int index, int limit);
    public int countByUid(long uid);
    public List<Order> findAllByState(int state, int index, int limit);
    public int countByState(int state);

    @PutMapping("/order/updateState/{id}/{state}/{aid}")
    public void updateState(@PathVariable("id") long id, @PathVariable("state") long state, @PathVariable("aid") long aid);

    @DeleteMapping("/deleteByMid/{mid}")
    public void deleteByMid(@PathVariable long mid);

    @DeleteMapping("/deleteByUid/{uid}")
    public void deleteByUid(@PathVariable long uid);
}
