package com.coder.study.controller.frontdesk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.service.CQService;
import com.coder.study.pojo.Commonquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import com.itbaizhan.study.bean.RoleWithStatus;
//import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/frontdesk/tea_commquestion")
public class TeaCQController {
    @Autowired
    private CQService cqService;

    @RequestMapping("/all")
   // @PreAuthorize("hasAnyAuthority('/admin/all')")
    //分页查询所有常见问题
    public ModelAndView stuAllCq(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<Commonquestion> commonquestionPage = cqService.findCQPage(page, size);
        modelAndView.addObject("cqPage",commonquestionPage);
        modelAndView.setViewName("/frontdesk/tea_cq_all");
        return modelAndView;
    }



//使用搜索框模糊查询
@RequestMapping("/search_comm")
// @PreAuthorize("hasAnyAuthority('/admin/all')")
public ModelAndView allAq(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int size,String des){
    ModelAndView modelAndView = new ModelAndView();
    Page<Commonquestion> commquestionPage = cqService.findSearchCQPage(page, size,des);
    modelAndView.addObject("cqPage",commquestionPage);
    modelAndView.setViewName("/frontdesk/tea_cq_all");
    return modelAndView;
}
//删除常见问题



//修改常见问题
    // 查询常见，跳转到修改页面
@RequestMapping("/edit")
    public ModelAndView edit(Integer commqid) {
        Commonquestion commonquestion =cqService .findById(commqid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cq", commonquestion);
        modelAndView.setViewName("/frontdesk/tea_cq_edit");
        return modelAndView;
    }
    @RequestMapping("/add")
    public String add(Commonquestion commonquestion){
        cqService.add(commonquestion);
        return "redirect:/frontdesk/tea_commquestion/all";
    }

    // 修改
    @RequestMapping("/update")
    public String update(Commonquestion commonquestion) {
        cqService.update(commonquestion);
        return "redirect:/frontdesk/tea_commquestion/all";
    }
    //删除

    @RequestMapping("/dele")
    public String delete(Integer commqid){
        cqService.delete(commqid);
        return "redirect:/frontdesk/tea_commquestion/all";
    }

    //查看常见详情
    @RequestMapping("/check")
    public ModelAndView desc(Integer commqid){
        Commonquestion commonquestion = cqService.findById(commqid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cq", commonquestion);
        modelAndView.setViewName("/frontdesk/tea_cq_desc");
        return modelAndView;
    }

}
