package com.coder.study.controller.frontdesk;

import com.coder.study.service.StudentService;
import com.coder.study.bean.Result;
import com.coder.study.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/frontdesk/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/register")
    public ModelAndView register(StudentInfo studentInfo){
        ModelAndView modelAndView = new ModelAndView();

        // 注册
        Result result = studentService.register(studentInfo);
        if (!result.isFlag()){
            modelAndView.addObject("message",result.getMessage());
            modelAndView.setViewName("/frontdesk/register");
        }else { // 注册成功
            modelAndView.setViewName("/frontdesk/register_ok");
        }
        return modelAndView;
    }
    @RequestMapping("/login")
    public ModelAndView login(String name, String password, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Result result = studentService.login(name, password);
        if (!result.isFlag()){ // 登录失败
            modelAndView.addObject("message",result.getMessage());
            modelAndView.setViewName("/frontdesk/student_login");
        }else{ // 登录成功
            // 将用户信息存入session
            session.setAttribute("studentInfo",result.getData());

            modelAndView.setViewName("redirect:/frontdesk/stu_index");
        }
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("studentInfo");
        System.out.println("消除stusession");
        session.invalidate();
        return "redirect:/frontdesk/student_login";
    }
    // 查询学生，跳转到修改页面
    @RequestMapping("/find")
    public ModelAndView find(Integer sid) {
        StudentInfo studentInfo = studentService.findById(sid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentInfo", studentInfo);
        modelAndView.setViewName("/frontdesk/stu_my_info");
        return modelAndView;
    }
    // 查询学生，跳转到修改页面
    @RequestMapping("/edit")
    public ModelAndView edit(Integer sid) {
        StudentInfo studentInfo = studentService.findById(sid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentInfo", studentInfo);
        modelAndView.setViewName("/frontdesk/my_stu_edit");
        return modelAndView;
    }

    // 修改学生个人信息
    @RequestMapping("/update")
    public String update(StudentInfo studentInfo) {
        studentService.update(studentInfo);
        return "redirect:/frontdesk/student_login";
    }
}
