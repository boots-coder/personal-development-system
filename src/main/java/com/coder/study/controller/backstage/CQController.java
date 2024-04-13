package com.coder.study.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coder.study.pojo.Commonquestion;
import com.coder.study.service.CQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import com.itbaizhan.study.bean.RoleWithStatus;
//import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/backstage/commquestion")
public class CQController {
    @Autowired
    private CQService cqService;

    @RequestMapping("/all")
   // @PreAuthorize("hasAnyAuthority('/admin/all')")
    //分页查询
    public ModelAndView allCq(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size){
        ModelAndView modelAndView = new ModelAndView();
        Page<Commonquestion> commonquestionPage = cqService.findCQPage(page, size);
        modelAndView.addObject("cqPage",commonquestionPage);
        modelAndView.setViewName("/backstage/cq_all");
        return modelAndView;
    }

//置顶功能
@RequestMapping("/betop")
    public String beTop(Integer commqid){
  cqService.beTop(  cqService.findById(commqid)
  );
        return "redirect:/backstage/commquestion/all" ;
    }
    @RequestMapping("/deletop")
    //取消置顶功能
    public String deleTop(Integer commqid){

        cqService.deleTop(  cqService.findById(commqid)
        );
        return "redirect:/backstage/commquestion/all" ;
    }


    // 查询常见，跳转到修改页面
    @RequestMapping("/edit")
    public ModelAndView edit(Integer commqid) {
        Commonquestion commonquestion =cqService .findById(commqid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cq", commonquestion);
        modelAndView.setViewName("/backstage/cq_edit");
        return modelAndView;
    }

@RequestMapping("/add")
public String add(Commonquestion commonquestion){
        cqService.add(commonquestion);
        return "redirect:/backstage/commquestion/all";
}

    //查看常见详情
    @RequestMapping("/check")
    public ModelAndView desc(Integer commqid){
        Commonquestion commonquestion = cqService.findById(commqid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cq", commonquestion);
        modelAndView.setViewName("/backstage/cq_desc");
        return modelAndView;
    }

    // 修改
    @RequestMapping("/update")
    public String update(Commonquestion commonquestion) {
        cqService.update(commonquestion);
        return "redirect:/backstage/commquestion/all";
    }
    //删除

    @RequestMapping("/delete")
    public String delete(Integer commqid){
        cqService.delete(commqid);
        return "redirect:/backstage/commquestion/all";
    }

}
