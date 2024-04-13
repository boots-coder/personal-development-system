package com.coder.study.controller.frontdesk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.service.CQService;
import com.coder.study.pojo.Commonquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/frontdesk/stu_commquestion")
public class StuCQController {
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
        modelAndView.setViewName("/frontdesk/stu_cq_all");
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
    modelAndView.setViewName("/frontdesk/stu_cq_all");
    return modelAndView;
}

    //查看常见详情
    @RequestMapping("/check")
    public ModelAndView desc(Integer commqid){
        Commonquestion commonquestion = cqService.findById(commqid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cq", commonquestion);
        modelAndView.setViewName("/frontdesk/stu_cq_desc");
        return modelAndView;
    }

}
