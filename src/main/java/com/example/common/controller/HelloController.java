package com.example.common.controller;


import com.example.common.model.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dt")
public class HelloController {


    @GetMapping("/hello")
    @ResponseBody
    public R getHello() {
        return R.ok("添加成功！");
    }

    @GetMapping("/view")
    public String getView(Model model) {
        model.addAttribute("hello","hello world") ;
       return "hello";
    }
}
