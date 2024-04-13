package com.coder.study.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    // 访问后台页面
    @RequestMapping("/backstage/{page}")
    public String showPageBackstage(@PathVariable String page){
        return "/backstage/"+page;
    }

    // 访问前台页面
    @RequestMapping("/frontdesk/{page}")
    public String showPageFrontdesk(@PathVariable String page){


        return "/frontdesk/"+page;
    }

    // 忽略访问项目logo
    @RequestMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon(){}
    /*// 页面跳转
    @GetMapping("/backstage/show")
    public String showPage(Model model){
        model.addAttribute("msg","Hello Thymeleaf");
        return "/backstage/"+"用户信息管理";
    }*/
}
