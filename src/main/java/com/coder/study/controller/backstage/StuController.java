package com.coder.study.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.service.StudentService;
import com.coder.study.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import com.itbaizhan.study.bean.RoleWithStatus;
//import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/backstage/student")
public class StuController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/all")
   // @PreAuthorize("hasAnyAuthority('/admin/all')")
    public ModelAndView allStu(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<StudentInfo> stuPage = studentService.findStuPage(page, size);
        modelAndView.addObject("stuPage",stuPage);
        modelAndView.setViewName("/backstage/stu_all");
        return modelAndView;
    }


    // 查询教师，跳转到修改页面
    @RequestMapping("/edit")
    public ModelAndView edit(Integer sid) {
        StudentInfo studentInfo = studentService.findById(sid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentInfo", studentInfo);
        modelAndView.setViewName("/backstage/stu_edit");
        return modelAndView;
    }

    // 修改教师
    @RequestMapping("/update")
    public String update(StudentInfo studentInfo) {
        studentService.update(studentInfo);
        return "redirect:/backstage/student/all";
    }
    //删除教师
    @RequestMapping("/dele")
    public String delete(Integer sid){
        studentService.delete(sid);
        return "redirect:/backstage/student/all";
    }

}
