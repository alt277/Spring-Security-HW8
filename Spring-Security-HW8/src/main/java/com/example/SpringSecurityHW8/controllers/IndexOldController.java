package com.example.SpringSecurityHW8.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/index-old")
public class IndexOldController {

    @GetMapping
    public String main(
            Model model
    ){
        model.addAttribute("name", "Alex");
//        model.addAttribute("visible", false);
        model.addAttribute("userRole", "manager");

        Map<String, Object> map = new HashMap<>();
        map.put("car", "Audi");
        map.put("bicycle", "GT");
        model.addAttribute("params", map);

        model.addAttribute("isPrimary", false);
//
        Double[] values = {100.,50.,35.,110.};
        model.addAttribute("values", values);
        return "index-old";
    }
}
