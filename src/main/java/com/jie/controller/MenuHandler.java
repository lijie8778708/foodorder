package com.jie.controller;

import com.jie.entity.Menu;
import com.jie.entity.MenuVO;
import com.jie.entity.Type;
import com.jie.repository.MenuRepository;
import com.jie.repository.OrderRepository;
import com.jie.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/findMenu")
    @ResponseBody
    public MenuVO findMenu(@RequestParam("page") int page, @RequestParam("limit") int limit){
        MenuVO menuVO = new MenuVO();
        menuVO.setCode(0);
        menuVO.setMsg("");
        menuVO.setCount(menuRepository.count());
        menuVO.setData(menuRepository.findMenu((page-1)*limit,limit));
        return menuVO;
    }

    @GetMapping("/findType")
    public List<Type> findType(){
        return typeRepository.findType();
    }

    @PostMapping("/update")
    public String update(Menu menu){
        menuRepository.update(menu);
        return "redirect:/account/redirect/menu_manage";
    }

    @GetMapping("/findById/{id}")
    public String findById(@PathVariable("id") long id, Model model){
        model.addAttribute("list", typeRepository.findType());
        model.addAttribute("menu", menuRepository.findById(id));
        return "/menu_update";
    }

    @GetMapping("/prepareSave")
    public String prepareSave(Model model){
        model.addAttribute("list",typeRepository.findType());
        return "/menu_add";
    }

    @PostMapping("/save")
    public String save(Menu menu){
        menuRepository.save(menu);
        return "redirect:/account/redirect/menu_manage";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        orderRepository.deleteByMid(id);
        menuRepository.deleteById(id);
        return "redirect:/account/redirect/menu_manage";
    }
}
