package com.jie.controller;

import com.jie.entity.User;
import com.jie.entity.UserVO;
import com.jie.repository.OrderRepository;
import com.jie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/findUser")
    @ResponseBody
    public UserVO findMenu(@RequestParam("page") int page, @RequestParam("limit") int limit){
        UserVO userVO = new UserVO();
        userVO.setCode(0);
        userVO.setMsg("");
        userVO.setCount(userRepository.count());
        userVO.setData(userRepository.findUser((page-1)*limit,limit));
        return userVO;
    }

    @PostMapping("/save")
    public String save(User user){
        user.setRegisterdate(new Date());
        userRepository.save(user);
        return "redirect:/account/redirect/user_manage";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        orderRepository.deleteByUid(id);
        userRepository.deleteById(id);
        return "redirect:/account/redirect/user_manage";
    }
}
