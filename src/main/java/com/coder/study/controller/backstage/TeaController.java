package com.coder.study.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.pojo.TeacherInfo;
import com.coder.study.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import com.itbaizhan.study.bean.RoleWithStatus;
//import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/backstage/teacher")
public class TeaController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/all")
   // @PreAuthorize("hasAnyAuthority('/admin/all')")
    public ModelAndView allTea(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<TeacherInfo> teaPage = teacherService.findTeaPage(page, size);
        modelAndView.addObject("teaPage",teaPage);
        modelAndView.setViewName("/backstage/tea_all");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(TeacherInfo teacherInfo) {
        teacherService.add(teacherInfo);
        return "redirect:/backstage/teacher/all";
    }
    // 查询教师，跳转到修改页面
    @RequestMapping("/edit")
    public ModelAndView edit(Integer tid) {
        TeacherInfo teacherInfo = teacherService.findById(tid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacherInfo", teacherInfo);
        modelAndView.setViewName("/backstage/tea_edit");
        return modelAndView;
    }

    // 修改教师
    @RequestMapping("/update")
    public String update(TeacherInfo teacherInfo) {
        teacherService.update(teacherInfo);
        return "redirect:/backstage/teacher/all";
    }
    //删除教师
    @RequestMapping("/delete")
    public String delete(Integer tid){
        teacherService.delete(tid);
        return "redirect:/backstage/teacher/all";
    }

   /* @RequestMapping("/add")
    public String add(Admin admin){
        adminService.add(admin);
        return "redirect:/backstage/admin/all";
    }

    // 查询管理员，跳转到修改页面
    @RequestMapping("/edit")
    public ModelAndView edit(Integer aid){
        Admin admin = adminService.findById(aid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin",admin);
        modelAndView.setViewName("/backstage/admin_edit");
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Admin admin){
        adminService.update(admin);
        return "redirect:/backstage/admin/all";
    }

    @RequestMapping("/desc")
    public ModelAndView desc(Integer aid){
        Admin admin = adminService.findDesc(aid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin",admin);
        modelAndView.setViewName("/backstage/admin_desc");
        return modelAndView;
    }

    @RequestMapping("/findRole")
    public ModelAndView findRole(Integer aid){
        List<RoleWithStatus> roles = adminService.findRole(aid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles",roles);
        modelAndView.addObject("aid",aid);
        modelAndView.setViewName("/backstage/admin_role");
        return modelAndView;
    }

    @RequestMapping("/updateRole")
    public String updateRole(Integer aid,Integer[] ids){
        adminService.updateRole(aid,ids);
        return "redirect:/backstage/admin/all";
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Integer aid){
        adminService.updateStatus(aid);
        return "redirect:/backstage/admin/all";
    }*/
}
