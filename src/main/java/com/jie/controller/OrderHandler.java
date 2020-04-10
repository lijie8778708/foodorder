package com.jie.controller;

import com.jie.entity.*;
import com.jie.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session){
        User user = (User) session.getAttribute("user");
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByUid(user.getId()));
        orderVO.setData(orderRepository.findAllByUid(user.getId(), (page-1)*limit, limit));
        return orderVO;
    }

    @GetMapping("/findAllByState")
    @ResponseBody
    public OrderVO findAllByState(@RequestParam("page") int page, @RequestParam("limit") int limit){
        OrderVO orderVO = new OrderVO();
        orderVO.setCode(0);
        orderVO.setMsg("");
        orderVO.setCount(orderRepository.countByState(0));
        orderVO.setData(orderRepository.findAllByState(0, (page-1)*limit, limit));
        return orderVO;
    }

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") long mid, HttpSession session){
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        Menu menu = new Menu();
        menu.setId(mid);
        order.setUser(user);
        order.setMenu(menu);
        order.setDate(new Date());
        orderRepository.save(order);
        return "redirect:/account/redirect/order";
    }

    @GetMapping("/updateState/{id}/{state}")
    public String updateState(@PathVariable("id") long id,@PathVariable("state") long state, HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        orderRepository.updateState(id, state, admin.getId());
        return "redirect:/account/redirect/order_handler";
    }
}
