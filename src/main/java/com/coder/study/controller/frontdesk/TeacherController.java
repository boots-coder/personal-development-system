package com.coder.study.controller.frontdesk;

import com.coder.study.pojo.TeacherInfo;
import com.coder.study.service.TeacherService;
import com.coder.study.bean.Result;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/frontdesk/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/login")
    public ModelAndView login(String name, String password, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Result result = teacherService.login(name, password);
        if (!result.isFlag()){ // 登录失败
            modelAndView.addObject("message",result.getMessage());
            modelAndView.setViewName("/frontdesk/teacher_login");
        }else{ // 登录成功
            // 将用户信息存入session
            session.setAttribute("teacherInfo",result.getData());

            modelAndView.setViewName("redirect:/frontdesk/tea_index");
        }
        return modelAndView;
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("teacherInfo");
        session.invalidate();
        System.out.println("消除session");
        return "redirect:/frontdesk/teacher_login";
    }
    // 查询教师，跳转到修改页面
    @RequestMapping("/edit")
    public ModelAndView edit(Integer tid) {
        TeacherInfo teacherInfo = teacherService.findById(tid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacherInfo", teacherInfo);
        modelAndView.setViewName("/frontdesk/my_tea_edit");
        return modelAndView;
    }

    // 修改教师个人信息
    @RequestMapping("/update")
    public String update(TeacherInfo teacherInfo) {
        teacherService.update(teacherInfo);
        return "redirect:/frontdesk/teacher_login";
    }


    // 查询学生，跳转到修改页面
    @RequestMapping("/find")
    public ModelAndView find(Integer tid) {
        TeacherInfo teacherInfo = teacherService.findById(tid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacherInfo", teacherInfo);
        modelAndView.setViewName("/frontdesk/tea_my_info");
        return modelAndView;
    }
}
