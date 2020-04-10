package com.jie.controller;

import com.jie.entity.Account;
import com.jie.entity.Admin;
import com.jie.entity.User;
import com.jie.repository.AdminRepository;
import com.jie.repository.UserRepository;
import com.jie.utils.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session){
        Account account = tryLogin(username,password,type);
        String target = null;
        if(account == null){
            target = "login";
        }else{
            switch (type){
                case "user":
                    User user = convertUser(account);
                    session.setAttribute("user",user);
                    target = "redirect:/account/redirect/index";
                    break;
                case "admin":
                    Admin admin = convertAdmin(account);
                    session.setAttribute("admin",admin);
                    target = "redirect:/account/redirect/main";
                    break;
            }
        }
        return target;
    }

    @GetMapping("/login/{username}/{password}/{type}")
    private Account tryLogin(String username, String password, String type){
        Account account = null;
        switch (type){
            case "user":
                account = userRepository.login(username, password);
                break;
            case "admin":
                account = adminRepository.login(username, password);
                break;
        }
        return account;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("/redirect/{target}")
    public String redirect(@PathVariable("target") String target){
        return target;
    }

    private User convertUser(Account account){
        User user = new User();
        user.setUsername(ReflectUtils.getFieldValue(account, "username")+"");
        user.setPassword(ReflectUtils.getFieldValue(account, "password")+"");
        user.setGender(ReflectUtils.getFieldValue(account,"gender")+"");
        user.setId((long)(ReflectUtils.getFieldValue(account,"id")));
        user.setNickname(ReflectUtils.getFieldValue(account,"nickname")+"");
        user.setRegisterdate((Date)(ReflectUtils.getFieldValue(account,"registerdate")));
        user.setTelephone(ReflectUtils.getFieldValue(account,"telephone")+"");
        return user;
    }
    private Admin convertAdmin(Account account){
        Admin admin = new Admin();
        admin.setUsername(ReflectUtils.getFieldValue(account,"username")+"");
        admin.setPassword(ReflectUtils.getFieldValue(account,"password")+"");
        admin.setId((long)(ReflectUtils.getFieldValue(account,"id")));
        return admin;
    }
}
